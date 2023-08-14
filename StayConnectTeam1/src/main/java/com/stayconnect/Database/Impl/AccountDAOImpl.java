package com.stayconnect.Database.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.stayconnect.Database.AccountDAO;
import com.stayconnect.model.Job;
import com.stayconnect.model.Profile;
import com.stayconnect.model.UserAccount;

@Repository
public class AccountDAOImpl implements AccountDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private DataSourceTransactionManager transactionManager;
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	@Override
	public UserAccount addAccount(UserAccount account) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "insert into user_account (email, password, authority, active) " + " values (?, ?, ?, ?);";
			jdbcTemplate.update(SQL, account.getEmail(), account.getPassword(),
					"ROLE_" + account.getAuthority(),account.isActive());
			
			logger.info("Created account: " + account.getEmail());
			// Adds minimal information required for User Account Profile
			SQL = "insert into profile (email, first_name, last_name) " + " values (?, ?, ?);";
			Profile profile = account.getProfile();
			jdbcTemplate.update(SQL, profile.getEmail(),profile.getFirstName(),profile.getLastName());
			
			logger.info("Added Minimal information needed for User Profile: " + profile.getFirstName() +" "+profile.getLastName() );
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in creating account record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return account;
	}

	@Override
	public UserAccount getAccountByEmail(String email) {
		String SQL = "select * from user_account where email = ?;";

		UserAccount account = jdbcTemplate.queryForObject(SQL, new AccountMapper(), new Object[] { email });
		
		logger.info("Account email: " + account.getEmail());
		
		return account;
	} 
	
	@Override
	public UserAccount deleteAccount(UserAccount account) {
		String SQL = "select * from user_account where email = ?;";
		
		SQL = "select * from job where email = ?;";
		List<Job> jobs = jdbcTemplate.query(SQL, new JobMapper(), new Object [] { account.getEmail() } );
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		// Remove every job posted by account
		for (Job job : jobs) {
			int id = job.getId();
			try {
				// delete from job table

				SQL = "delete from job_technical_skill where job_id = ?";	
				jdbcTemplate.update(SQL, id);
				
				SQL = "delete from job_location where job_id = ?";	
				jdbcTemplate.update(SQL, id);
				
				SQL = "delete from job where job_id = ?";		
				jdbcTemplate.update(SQL, id);
				// commit the transaction
				logger.info("Removed Job Name = " + job.getTitle());
			} catch (DataAccessException e) {
				System.out.println("Error in removing job, rolling back");
				transactionManager.rollback(status);
				throw e;
			}
		}
		try {
			// delete account
			String email = account.getEmail();
			SQL = "delete from profile_technical_skill where email = ?";	
			jdbcTemplate.update(SQL, email);
			
			SQL = "delete from work_experience where email = ?";		
			jdbcTemplate.update(SQL, email);
			
			SQL = "delete from profile_phone_number where email = ?";	
			jdbcTemplate.update(SQL, email);
			
			SQL = "delete from profile_job_title where email = ?";		
			jdbcTemplate.update(SQL, email);
			
			SQL = "delete from profile where email = ?";		
			jdbcTemplate.update(SQL, email);
			
			SQL = "delete from user_account where email = ?";		
			jdbcTemplate.update(SQL, email);
			// commit the transaction
			transactionManager.commit(status);	
			logger.info("Removed Job Name = " + account.getEmail());
		} catch (DataAccessException e) {
			System.out.println("Error in removing job, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return account;
	}
	
	@Override
	public int updateActive(String email, boolean active) {
		String SQL = "UPDATE user_account " 
				+ "SET active=? " 
				+ "WHERE email=?;";
		int affectedRow = 0;
		try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setBoolean(1, active);
            pstmt.setString(2, email);

            affectedRow = pstmt.executeUpdate();
            logger.info("Set " + email + "active to " + active);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return affectedRow;
	}
	
	private String url = "jdbc:postgresql://lovelace.cs.scranton.edu/se516_1_2022";
	private String user = "dbuser1";
	private String pass = "kirk123";
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	@Override
	public List<String> getAuthorities() {
		String sql = "select * from authorities;";
		List<String> authorities = new ArrayList<String>();
		authorities = jdbcTemplate.query(sql, new AuthorityMapper());
		return authorities;
	}
	
	private class JobMapper implements RowMapper<Job> {
    	public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Job job = new  Job();
    		job.setId(rs.getInt("job_id"));
    		job.setTitle(rs.getString("job_title"));
    		job.setType(rs.getString("job_type"));
    		job.setDescription(rs.getString("job_description"));
    		job.setCompanyName(rs.getString("company_name"));
    		job.setPoster_email(rs.getString("email"));

			return job;
    	}
    }
	
	class AccountMapper implements RowMapper<UserAccount> {

		@Override
		public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAccount account = new UserAccount();
			account.setEmail(rs.getString("email"));
			account.setPassword(rs.getString("password"));
			account.setActive(rs.getBoolean("active"));
			account.setAuthority(rs.getString("authority"));
			return account; 
		}
		
	}
	
	class AuthorityMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String role = rs.getString("authority");
			return role;
		}
		
	}
}
