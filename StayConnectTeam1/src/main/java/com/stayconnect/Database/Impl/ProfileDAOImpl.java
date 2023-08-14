package com.stayconnect.Database.Impl;

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

import com.stayconnect.Database.ProfileDAO;
import com.stayconnect.model.Profile;
import com.stayconnect.model.ProfileSearchMethod;
import com.stayconnect.model.WorkExperience;



@Repository
public class ProfileDAOImpl implements ProfileDAO {
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
	public Profile addProfile(Profile p) {
		// TODO Auto-generated method stub
		return null;
	}
	public WorkExperience addWorkExperience(WorkExperience workExperience) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "insert into work_experience (email, title,  work_description, employment_type, employer_name,"
					+ "position, start_month, start_year, end_month, end_year, city, state, zip_code) " + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			jdbcTemplate.update(SQL,workExperience.getEmail(), workExperience.getTitle(), workExperience.getDescription(), workExperience.getType(),
					workExperience.getCompanyName(),workExperience.getPosition(),workExperience.getStartMonth(),workExperience.getStartYear(),
					workExperience.getEndMonth(),workExperience.getEndYear(),workExperience.getCity(),workExperience.getState(),workExperience.getZipcode());
			
			logger.info("Work Experience Added: " + workExperience.getTitle());
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in adding Work Experience record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return workExperience;
	}
	public List<WorkExperience> getWorkExperiences(String email){
		String SQL = "SELECT * FROM work_experience WHERE email = ?";
		
		List<WorkExperience> workExperiences = jdbcTemplate.query(SQL,new WorkExperienceMapper(), new Object[] { email}); 
		logger.info("Size of Work Experience: "+ workExperiences.size());
		return workExperiences;
	}
	@Override
	public Profile updateProfile(Profile p, String email) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set first_name = ?, last_name = ?, bio = ?, company_name = ?, city = ?, "
					+ "state = ?, zip_code = ?, office_number = ?, graduation_year = ?, major = ? " + "where email = ?;";
			
			jdbcTemplate.update(SQL,p.getFirstName(),p.getLastName(),p.getBiography(),p.getCompany_name(),p.getCity1(),p.getState1(),p.getZipcode1(),
									p.getOfficeNum(),p.getGraduationYear(),p.getMajor(), email);
			
			logger.info("Updated User Profile: " + p.getFirstName() + p.getLastName());
			

			SQL = "insert into profile_job_title (email, job_title) " + " values (?, ?)";
//			SQL =  "UPDATE profile_job_title " 
//					+ "SET =? " 
//					+ "WHERE email=?;";
			if(p.getJobtitle1() != "") {
				
				jdbcTemplate.update(SQL,email,p.getJobtitle1());
			}
			if(p.getJobtitle2() != "") {
				jdbcTemplate.update(SQL,email,p.getJobtitle2());
				
			}
			if(p.getJobtitle3() != "") {
				jdbcTemplate.update(SQL,email,p.getJobtitle3());
				
			}
			
			SQL = "insert into profile_phone_number (email, phone_number) " + " values (?, ?)";
			if(p.getPhone1() != "") {
				jdbcTemplate.update(SQL,email,p.getPhone1());
				
			}
			if(p.getPhone2() != "") {
				jdbcTemplate.update(SQL,email,p.getPhone2());
				
			}
			if(p.getPhone3() != "") {
				
				jdbcTemplate.update(SQL,email,p.getPhone3());
			}
			
			SQL = "insert into profile_technical_skill (email, technical_skill) " + " values (?, ?)";
			if(p.getSkill1() != "") {
				
				jdbcTemplate.update(SQL,email,p.getSkill1());
			}
			if(p.getSkill2() != "") {
				jdbcTemplate.update(SQL,email,p.getSkill2());
				
			}
			if(p.getSkill3() != "") {
				
				jdbcTemplate.update(SQL,email,p.getSkill3());
			}
			if(p.getSkill4() != "") {
				jdbcTemplate.update(SQL,email,p.getSkill4());
				
			}
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in creating account record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return p;
	}

	@Override
	public Profile deleteProfile(Profile p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile getProfiles(ProfileSearchMethod method) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
	public Profile getProfileByEmail(String email) {
    	String SQL = "select * from profile where email = ?";
    	Profile profile = jdbcTemplate.queryForObject(SQL, new ProfileMapper(), new Object[] { email });
    	logger.info("Retrieved Profile Name = " + profile.getEmail());
    	
    	//Set retrieved skills, phone numbers, and profile titles to the profile to be returned
		profile = setProfileSkills(email, profile);
		profile = setProfilePhoneNumbers(email, profile); 	
    	profile = setProfileTitles(email, profile);
    	
		return profile;
	}

	private Profile setProfileTitles(String email, Profile profile) {
		String SQL = "select job_title from profile_job_title where email = ?";
    	List<String> jobtitles = jdbcTemplate.queryForList(SQL,String.class, new Object[] { email });
    	int size = jobtitles.size();
    	if(size != 0) {
    		profile.setJobtitle1(jobtitles.get(--size));  		
    	}
    	if(size != 0) {
    		profile.setJobtitle2(jobtitles.get(--size));  		
    	}
    	if(size != 0) {
    		profile.setJobtitle3(jobtitles.get(--size));	
    	}
    	return profile;
	}
	private Profile setProfilePhoneNumbers(String email, Profile profile) {
		String SQL = "select phone_number from profile_phone_number where email = ?";
		List<String> phonenumbers = jdbcTemplate.queryForList(SQL,String.class, new Object[] { email });
		int size = phonenumbers.size();
		if(size != 0) {
			profile.setPhone1(phonenumbers.get(--size)); 		
		}
		if(size != 0) {
			profile.setPhone2(phonenumbers.get(--size)); 			
		}
		if(size != 0) {
			profile.setPhone3(phonenumbers.get(--size)); 	
		}
		return profile;
	}

	private Profile setProfileSkills(String email, Profile profile) {
		String SQL = "select technical_skill from profile_technical_skill where email = ?";
    	List<String> skills = jdbcTemplate.queryForList(SQL,String.class, new Object[] { email });
    	int size = skills.size();
    	if(size != 0) {
    		profile.setSkill1(skills.get(--size));    		
    	}
    	if(size != 0) {
    		profile.setSkill2(skills.get(--size));    		
    	}
    	if(size != 0) {
    		profile.setSkill3(skills.get(--size));    		
    	}
    	if(size != 0) {
    		profile.setSkill4(skills.get(--size));    		
    	}
    	if(size != 0) {
    		profile.setSkill5(skills.get(--size));    		
    	}
    	return profile;
	}
	@Override
	public List<Profile> searchProfilesByEmail(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE email = ? ";
		
		 List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				 new Object[] { profileSearchMethod.getEmail()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesByFirstName(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE first_name = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] { profileSearchMethod.getFirstName()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesByLastName(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE last_name = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] { profileSearchMethod.getLastName()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesCompanyName(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE company_name = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] {profileSearchMethod.getCompanyName()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesByCity(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE city = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] {profileSearchMethod.getCity()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesByState(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE state = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] {profileSearchMethod.getState()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesByZipCode(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE zip_code = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] {profileSearchMethod.getZip()}); 
		return profileSearchResult;
	}
	@Override
	public List<Profile> searchProfilesByAllSearchMethods(ProfileSearchMethod profileSearchMethod) {
		String SQL = "SELECT * FROM profile WHERE email = ? OR first_name = ? OR last_name = ? OR company_name = ? OR city = ? OR state = ? OR zip_code = ?";
		
		List<Profile> profileSearchResult	= jdbcTemplate.query(SQL,new ProfileMapper(), 
				new Object[] { profileSearchMethod.getEmail(), profileSearchMethod.getFirstName(),profileSearchMethod.getLastName(),
						profileSearchMethod.getCompanyName(),profileSearchMethod.getCity(),profileSearchMethod.getState(),
						profileSearchMethod.getZip()}); 
		return profileSearchResult;
	}
	
	@Override
	public List<Profile> getSomeProfiles() {
		List<Profile> profiles = new ArrayList<>();
		String SQL = "select * from profile LIMIT 6;";
		
		profiles = jdbcTemplate.query(SQL, new ProfileMapper());
		logger.info("PRofile City: "+ profiles.get(0).getCity1());
		return profiles;
	}
	@Override
	public Profile updateFirstName(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set first_name = ? where email = ?;";
			
			jdbcTemplate.update(SQL,profile.getFirstName(), profile.getEmail());
			
			logger.info("Updated User Profile: " + profile.getFirstName());
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating firstName record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}

		return profile;
	}

	@Override
	public Profile updateLastName(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set last_name = ? where email = ?;";
			
			jdbcTemplate.update(SQL, profile.getLastName(), profile.getEmail());
			
			logger.info("Updated User Profile: " + profile.getLastName());
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating Last Name record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}

		return profile;
	}

	@Override
	public Profile updateBiography(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set bio = ? where email = ?;";
			
			jdbcTemplate.update(SQL, profile.getBiography(), profile.getEmail());
			
			logger.info("Updated User Profile bio: " + profile.getBiography());
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating bio record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return profile;
	}

	@Override
	public Profile updateJobTitle(String jobtitle, String email) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "insert into profile_job_title (email, job_title) values(?, ?)";
			
			jdbcTemplate.update(SQL, email, jobtitle);
			
			logger.info("inserted User Profile Job Title: " + jobtitle);
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in inserted job title record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return null;
	}

	@Override
	public Profile updatePhone(String phone, String email) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "insert into profile_phone_number (email, phone_number) values(?, ?)";
			
			jdbcTemplate.update(SQL, email, phone);
			
			logger.info("inserted User Profile Phone Number: " + phone);
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in inserting phone record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return null;
	}

	@Override
	public Profile updateCity1(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set city = ? where email = ?;";
			
			jdbcTemplate.update(SQL, profile.getCity1(), profile.getEmail());
			
			logger.info("Updated User Profile city: " + profile.getCity1());
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating city record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return profile;
	}

	@Override
	public Profile updateState1(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set state = ? where email = ?;";
			
			jdbcTemplate.update(SQL, profile.getState1(), profile.getEmail());
			
			logger.info("Updated User Profile State: " + profile.getState1());
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating State record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return profile;
	}

	@Override
	public Profile updateZipCode1(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set zip_code = ? where email = ?;";
			
			jdbcTemplate.update(SQL, profile.getZipcode1(), profile.getEmail());
			
			logger.info("Updated User Profile ZipCode: " + profile.getZipcode1());
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating ZipCode record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return profile;
	}

	@Override
	public Profile updateGraduationYear(Profile profile) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "update profile set graduation_year = ? where email = ?;";
			
			jdbcTemplate.update(SQL, profile.getGraduationYear(), profile.getEmail());
			
			logger.info("Updated User Profile Graduation Year: " + profile.getGraduationYear());
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in updating Graduation Year record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return profile;
	}

	@Override
	public Profile updateSkill(String skill, String email) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String SQL = "insert into profile_technical_skill (email, technical_skill) values(?, ?)";
			
			jdbcTemplate.update(SQL, email, skill);
			
			logger.info("inserted User Profile Skill: " + skill);
		
			
			transactionManager.commit(status);
		} catch (DataAccessException e){
			System.out.println("Error in inserting skill record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return null;
	}

	
// email | first_name | last_name | bio | company_name | city | state | zip_code | office_number | graduation_year | major
    private class ProfileMapper implements RowMapper<Profile> {
    	public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Profile profile = new Profile();
    		profile.setEmail(rs.getString("email"));
    		profile.setFirstName(rs.getString("first_name"));
    		profile.setLastName(rs.getString("last_name"));
    		profile.setBiography(rs.getString("bio"));
    		profile.setCompany_name(rs.getString("company_name"));
    		profile.setCity1(rs.getString("city"));
    		profile.setState1(rs.getString("state"));
    		profile.setZipcode1(rs.getString("zip_code"));
    		profile.setOfficeNum(rs.getString("office_number"));
    		profile.setMajor(rs.getString("major"));
    		profile.setGraduationYear(rs.getString("graduation_year"));
			return profile;
    	}
    }
    private class WorkExperienceMapper implements RowMapper<WorkExperience> {
    	public WorkExperience mapRow(ResultSet rs, int rowNum) throws SQLException {
    		WorkExperience workExperience = new WorkExperience();
    		workExperience.setEmail(rs.getString("email"));
    		workExperience.setTitle(rs.getString("title"));
    		workExperience.setDescription(rs.getString("work_description"));
    		workExperience.setType(rs.getString("employment_type"));
    		workExperience.setCompanyName(rs.getString("employer_name"));
    		workExperience.setPosition(rs.getString("position"));
    		workExperience.setStartMonth(rs.getString("start_month"));
    		workExperience.setStartYear(rs.getString("start_year"));
    		workExperience.setEndMonth(rs.getString("end_month"));
    		workExperience.setEndYear(rs.getString("end_year"));
    		workExperience.setCity(rs.getString("city"));
    		workExperience.setState(rs.getString("state"));
    		workExperience.setZipcode(rs.getString("zip_code"));
    		return workExperience;
    	}
    }
}
