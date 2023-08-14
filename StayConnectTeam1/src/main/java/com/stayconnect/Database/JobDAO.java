package com.stayconnect.Database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stayconnect.model.Job;
import com.stayconnect.model.JobSearchMethod;


@Repository
public interface JobDAO {
	public Job addJob(Job job);
	public List<Job> getResults(JobSearchMethod method);	
	public Job getJobById(int id);
	public Job getJobByEmail(String email);
	public Job getRecentJob(String title);
	public List<Job> getFiveMostRecentJobPost();
	public List<Job> getMyPostedJobs(String email);
	public Job removeMyPostedJob(int id);
	public List<Job> searchJobsByAllSearchMethods(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByCityStateZipCode(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByZipCode(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByTitle(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByType(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByState(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByCity(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsCompanyName(JobSearchMethod jobSearchMethod);
	public List<Job> searchJobsByEmail(JobSearchMethod jobSearchMethod);

}
