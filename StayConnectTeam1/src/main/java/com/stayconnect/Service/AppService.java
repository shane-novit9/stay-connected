package com.stayconnect.Service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.stayconnect.model.Job;
import com.stayconnect.model.JobSearchMethod;
import com.stayconnect.model.Profile;
import com.stayconnect.model.ProfileSearchMethod;
import com.stayconnect.model.UserAccount;
import com.stayconnect.model.WorkExperience;

@Service
public interface AppService {
	public UserAccount addAccount(UserAccount account);
	public UserAccount getAccountByEmail(String email);
	public UserAccount deleteAccount(UserAccount account);
	public Job addJob(Job job);
	public List<Job> searchJobs(JobSearchMethod method);
	public Job getJobById(int id);
	public List<Job> getFiveMostRecentJobPost();
	public Job getJobByEmail(String email);
	public Profile addProfile(Profile profile);
	public Profile updateProfile(Profile profile, String email);
	public Profile getProfileByEmail(String email);
	public List<Profile> getSomeProfiles();
	void updateActive(String email, boolean active);
	public List<String> getAuthorities();
	public List<Job> getMyPostedJobs(String email);
	public Job removeMyPostedJob(int id);
	public List<Profile> searchProfiles(ProfileSearchMethod profileSearchMethod);
	public WorkExperience addWorkExperience(WorkExperience workExperience);
	public List<WorkExperience> getWorkExperiences(String email);
}
