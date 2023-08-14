package com.stayconnect.Database.Impl;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

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

import com.stayconnect.Database.JobDAO;

import com.stayconnect.model.Job;
import com.stayconnect.model.JobSearchMethod;






@Repository
public class JobDAOImpl implements JobDAO {
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
    public Job addJob(Job job) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			// insert into account table
			String SQL = "insert into job (job_title,job_type,job_description,company_name,email) " + " values (?, ?, ?, ?, ?)";
			jdbcTemplate.update(SQL, job.getTitle(),job.getType(),job.getDescription(),job.getCompanyName(),job.getPoster_email());
			
	        // adds locations		
			if(job.getCity1() != "" && job.getState1() != "" & job.getZipcode1() != "") {
				addLocation(job.getCity1(), job.getState1(), job.getZipcode1(), job.getTitle());
			}
			if(job.getCity2() != "" && job.getState2() != "" && job.getZipcode2() != "") {
				addLocation(job.getCity2(), job.getState2(), job.getZipcode2(), job.getTitle());
			}
			if(job.getCity3() != "" && job.getState3() != "" && job.getZipcode3() != "") {
				addLocation(job.getCity3(), job.getState3(), job.getZipcode3(), job.getTitle());

			}
			if(job.getCity4() != "" && job.getState4() != "" && job.getZipcode4() != "") {
				addLocation(job.getCity4(), job.getState4(), job.getZipcode4(), job.getTitle());
			}
			// adds technical skills
			if(job.getSkill1() != "") {
				addTechnicalSkill(job.getSkill1(), job.getTitle());
			}
			if(job.getSkill2() != "") {
				addTechnicalSkill(job.getSkill2(), job.getTitle());
			}
			if(job.getSkill3() != "") {
				addTechnicalSkill(job.getSkill3(), job.getTitle());
			}
			if(job.getSkill4() != "") {
				addTechnicalSkill(job.getSkill4(), job.getTitle());
			}
			if(job.getSkill5() != "") {
				addTechnicalSkill(job.getSkill5(), job.getTitle());
			}
			// commit the transaction
			transactionManager.commit(status);	
			logger.info("Created Job Name = " + job.getTitle());
		} catch (DataAccessException e) {
			System.out.println("Error in creating a new account record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return job;
	}
    public List<Job> getFiveMostRecentJobPost(){
		String SQL = "select * from job";
		List<Job> recentJobs = jdbcTemplate.query(SQL, new JobMapper());
		List<Job> fiveMostRecentPostedJobs = new ArrayList<>();
		int count = 0;
		logger.info("5 Job Size = " + recentJobs.size());
		for(int i = recentJobs.size()-1; i > 0; i--) {
			if(count < 5) {
//				SQL = "select job_id from job_location (job_id) " + " values (?)";
				Job job = recentJobs.get(i);
				job = getJobById(job.getId());
				fiveMostRecentPostedJobs.add(job);
				logger.info("Count = " + count);
			}else {
				break;
			}
			
			count++;
		}
		
        logger.info("Retrieved 5 Most Recently Posted Jobs");
			
		return fiveMostRecentPostedJobs;
    }
    private String addTechnicalSkill(String technicalSkill, String jobTitle) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			
			Job recentJob = getRecentJob(jobTitle);

			String SQL = "insert into job_technical_skill (job_id, technical_skill) " + " values (?, ?)";

			jdbcTemplate.update(SQL,recentJob.getId(),technicalSkill);

			// commit the transaction
			transactionManager.commit(status);	
			logger.info("Technical Skill Added = " + technicalSkill);

		} catch (DataAccessException e) {
			System.out.println("Error in creating a new account record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return  technicalSkill;
	}
    private String[] addLocation(String city,String state,String zip, String jobTitle) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			
			Job recentJob = getRecentJob(jobTitle);

			String SQL = "insert into job_location (job_id, city, state, zip_code) " + " values (?, ?, ?, ?)";
			logger.info("Job City = " + city);

			jdbcTemplate.update(SQL,recentJob.getId(),city,state,zip);

			// commit the transaction
			transactionManager.commit(status);	
			logger.info("Created Job Name = " + city +","+state+","+zip);
		} catch (DataAccessException e) {
			System.out.println("Error in creating a new account record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		String[] location = {city,state,zip};
		return  location;
	}
    
	public Job getRecentJob(String title) {
		String SQL = "select * from job where job_title = ?";
		List<Job> jobs = jdbcTemplate.query(SQL, new JobMapper(), new Object[] { title });

		Job recentJob= new Job();
        if(jobs.size() != 0) {
        	recentJob = jobs.get(jobs.size()-1);        	
        }
		
        logger.info("Retrieved Recent Job Titled = " + title);
			
		return recentJob;
	}
	public Job getJobByEmail(String email) {
		logger.info("Email = " + email);
		String SQL = "select * from job where email = ?";
		List<Job> jobs = jdbcTemplate.query(SQL, new JobMapper(), new Object[] { email });
		logger.info("Job len= " + jobs.size());
		Job recentJob= new Job();
        if(jobs.size() != 0) {
        	recentJob = jobs.get(jobs.size()-1);        	
        }
        
        retrieveLocationOfJob(recentJob);
        return retrieveTechnicalSkillsOfJob(recentJob);
	}
	@Override
	public List<Job> getResults(JobSearchMethod method) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Job getJobById(int id) {
		logger.info("Id = " + id);
		String SQL = "select * from job where job_id = ?";
		List<Job> jobs = jdbcTemplate.query(SQL, new JobMapper(), new Object[] { id });
		logger.info("Job len= " + jobs.size());
		Job recentJob= new Job();
        if(jobs.size() != 0) {
        	recentJob = jobs.get(jobs.size()-1);        	
        }
        
        recentJob = retrieveLocationOfJob(recentJob);
        return retrieveTechnicalSkillsOfJob(recentJob);
	}
	private Job retrieveLocationOfJob(Job recentJob) {
		String SQL;
		//Retrieve Citys
        SQL = "select city from job_location where job_id = ?";
        List<String> citys = jdbcTemplate.queryForList(SQL,String.class, new Object[] { recentJob.getId() });

        if(citys.size() != 0) {
        	recentJob.setCity1(citys.get(0)); 
        	citys.remove(0);
        }
        if(citys.size() != 0) {
        	recentJob.setCity2(citys.get(0)); 
        	citys.remove(0);
        }
        if(citys.size() != 0) {
        	recentJob.setCity3(citys.get(0)); 
        	citys.remove(0);
        }
        if(citys.size() != 0) {
        	recentJob.setCity4(citys.get(0)); 
        	citys.remove(0);
        }

        //Retrieves states
        SQL = "select state from job_location where job_id = ?";
        List<String> states = jdbcTemplate.queryForList(SQL,String.class, new Object[] { recentJob.getId() });
        logger.info("Job states size= " + states.size());
        
        if(states.size() != 0) {
        	recentJob.setState1(states.get(0)); 
        	states.remove(0);
        }
        if(states.size() != 0) {
        	recentJob.setState2(states.get(0)); 
        	states.remove(0);
        }
        if(states.size() != 0) {
        	recentJob.setState3(states.get(0)); 
        	states.remove(0);
        }
        if(states.size() != 0) {
        	recentJob.setState4(states.get(0)); 
        	states.remove(0);
        }
        
//        //Retrieves zipcodes
        SQL = "select zip_code from job_location where job_id = ?";
        List<String> zipcodes = jdbcTemplate.queryForList(SQL,String.class, new Object[] { recentJob.getId() });

        if(zipcodes.size() != 0) {
        	recentJob.setZipcode1(zipcodes.get(0)); 
        	zipcodes.remove(0);
        }
        logger.info("Job Zip FM size= " + zipcodes.size());
        if(zipcodes.size() != 0) {
        	recentJob.setZipcode2(zipcodes.get(0)); 
        	zipcodes.remove(0);
        }
        logger.info("Job Zip FL size= " + zipcodes.size());
        if(zipcodes.size() != 0) {
        	recentJob.setZipcode3(zipcodes.get(0)); 
        	zipcodes.remove(0);
        }
        logger.info("Last Zip size = " + zipcodes.size());
        if(zipcodes.size() != 0) {
        	recentJob.setZipcode4(zipcodes.get(0)); 
        	zipcodes.remove(0);
        }

        logger.info("Retrieved Recent Job Titled With Email = " + recentJob.getTitle());
			
		return recentJob;
	}
	private Job retrieveTechnicalSkillsOfJob(Job recentJob) {
		String SQL;
		//Retrieve technical skills
        SQL = "select technical_skill from job_technical_skill where job_id = ?";
        List<String> technicalSkills = jdbcTemplate.queryForList(SQL,String.class, new Object[] { recentJob.getId() });

        if(technicalSkills.size() != 0) {
        	recentJob.setSkill1(technicalSkills.get(0)); 
        	technicalSkills.remove(0);
        }
        if(technicalSkills.size() != 0) {
        	recentJob.setSkill2(technicalSkills.get(0)); 
        	technicalSkills.remove(0);
        }
        if(technicalSkills.size() != 0) {
        	recentJob.setSkill3(technicalSkills.get(0)); 
        	technicalSkills.remove(0);
        }
        if(technicalSkills.size() != 0) {
        	recentJob.setSkill4(technicalSkills.get(0)); 
        	technicalSkills.remove(0);
        }
        if(technicalSkills.size() != 0) {
        	recentJob.setSkill5(technicalSkills.get(0)); 
        	technicalSkills.remove(0);
        }

 
        logger.info("Retrieved Recent Technical Skills Job Titled = " + recentJob.getTitle());
			
		return recentJob;
	}
	@Override
	public Job removeMyPostedJob(int id) {
		String SQL = "select * from job where job_id = ?";
		Job job = jdbcTemplate.queryForObject(SQL, new JobMapper(), new Object[] { id });
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			// delete from job table

			SQL = "delete from job_technical_skill where job_id = ?";	
			jdbcTemplate.update(SQL, id);
			
			SQL = "delete from job_location where job_id = ?";	
			jdbcTemplate.update(SQL, id);
			
			SQL = "delete from job where job_id = ?";		
			jdbcTemplate.update(SQL, id);
			// commit the transaction
			transactionManager.commit(status);	
			logger.info("Removed Job Name = " + job.getTitle());
		} catch (DataAccessException e) {
			System.out.println("Error in removing job, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		logger.info("Removed Job Title = " + job.getTitle());
		logger.info("Removed Job Id = " + job.getId());
		logger.info("Removed Job ID inputed = " + id);
		return job;
	}
	@Override
	public List<Job> getMyPostedJobs(String email) {
		String SQL = "select * from job where email = ?";
	    List<Job> myJobs = jdbcTemplate.query(SQL, new JobMapper(), new Object[] { email });
	    List<Job> myPostedJobs = new ArrayList<>();
	    Job myJob = new Job();
	    for(int i = myJobs.size()-1; i >= 0;  i--) {
	    	myJob = retrieveLocationOfJob(myJobs.get(i));
	    	myJob = retrieveTechnicalSkillsOfJob(myJob);
	    	myPostedJobs.add(myJob);
	    }

		return myPostedJobs;
	}
	@Override
	public List<Job> searchJobsByAllSearchMethods(JobSearchMethod jobSearchMethod) {
//		String SQL = "SELECT * FROM job WHERE job_title = ? OR job_type = ? OR company_name = ? OR email = ?";
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where job_title = ? or job_type = ? or company_name = ? or email = ? "+
				 "or city = ? or state = ? or zip_code = ?;";
		
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getTitle(), jobSearchMethod.getType(), jobSearchMethod.getCompanyName(),
						 jobSearchMethod.getEmail(), jobSearchMethod.getCity(), jobSearchMethod.getState(), jobSearchMethod.getZip()}); 
		 
		 logger.info("Retriec: "+ jobSearchResult.get(0).getTitle());
		return jobSearchResult;
	}
	public List<Job> searchJobsByCityStateZipCode(JobSearchMethod jobSearchMethod){
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where city = ? and state = ? and zip_code = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getCity(), jobSearchMethod.getState(),
						 jobSearchMethod.getZip()}); 
		return jobSearchResult;
	}
	@Override
	public List<Job> searchJobsByTitle(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where job_title = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getTitle()}); 
		return jobSearchResult;
	}
	@Override
	public List<Job> searchJobsByType(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where job_type = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getType()}); 
		return jobSearchResult;
	}
	@Override
	public List<Job> searchJobsByZipCode(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where zip_code = ?;";
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getZip()}); 
		return jobSearchResult;
	}
	@Override
	public List<Job> searchJobsByState(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where state = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL, new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getState()}); 
		return jobSearchResult;
	}
	@Override
	public List<Job> searchJobsByCity(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
					 "job.email, job_location.city, job_location.state, job_location.zip_code"+
				" from job join job_location on job.job_id = job_location.job_id where city = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getCity()}); 
		return jobSearchResult;
	}
	@Override
	public List<Job> searchJobsCompanyName(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where company_name = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getCompanyName()}); 
		return jobSearchResult;
	}

	@Override
	public List<Job> searchJobsByEmail(JobSearchMethod jobSearchMethod) {
		String SQL = "select job.job_id, job.job_title, job.job_type, job.company_name, " + 
				 "job.email, job_location.city, job_location.state, job_location.zip_code"+
			" from job join job_location on job.job_id = job_location.job_id where email = ?;";
		
		 List<Job> jobSearchResult	= jdbcTemplate.query(SQL,new SearchJobMapper(), 
				 new Object[] { jobSearchMethod.getEmail()}); 
		return jobSearchResult;
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
    private class SearchJobMapper implements RowMapper<Job> {
    	public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Job job = new  Job();
    		job.setId(rs.getInt("job_id"));
    		job.setTitle(rs.getString("job_title"));
    		job.setType(rs.getString("job_type"));
    		job.setCompanyName(rs.getString("company_name"));
    		job.setPoster_email(rs.getString("email"));
    		job.setCity1(rs.getString("city"));
    		job.setState1(rs.getString("state"));
    		job.setZipcode1(rs.getString("zip_code"));

			return job;
    	}
    }


}

