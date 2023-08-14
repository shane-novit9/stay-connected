package com.stayconnect.Database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stayconnect.model.Job;
import com.stayconnect.model.JobSearchMethod;
import com.stayconnect.model.Profile;
import com.stayconnect.model.ProfileSearchMethod;
import com.stayconnect.model.UserAccount;
import com.stayconnect.model.WorkExperience;

@Repository
public class AppRepository {
	@Autowired
	private JobDAO jobs;
	@Autowired
	private ProfileDAO profiles;
	@Autowired
	private AccountDAO accounts;


	public UserAccount addAccount(UserAccount account) {
		return accounts.addAccount(account);
	}
	public UserAccount getAccountByEmail(String email) {
		return accounts.getAccountByEmail(email);
	}
	public UserAccount deleteAccount(UserAccount account) {
		accounts.deleteAccount(account);
		return null;
	}
	public int updateActive(String email, boolean active) {
		return accounts.updateActive(email, active);
	}
	public List<String> getAuthorities() { return accounts.getAuthorities(); }
	public Job addJob(Job j) {
		return jobs.addJob(j);
	}

	public List<Job> getResultsByEmail(String email) {
		return null;
	}
	public List<Job> getResultsByCompany(String companyName) {
		return null;
	}
	public List<Job> getResultsByType(String type) {
		return null;
	}
	public List<Job> getResultsByTitle(String title) {
		return null;
	}	
	public Job getJobById(int id) {
		return jobs.getJobById(id);
	}
	public Job getJobByEmail(String email) {
		return jobs.getJobByEmail(email);
	}
	public List<Job> getFiveMostRecentJobPost(){
		return jobs.getFiveMostRecentJobPost();
	}
	public Profile addProfile(Profile p) {return null;}
	public Profile updateProfile(Profile p, String email) {
		return profiles.updateProfile(p, email);
	}
	public Profile getProfileByEmail(String email) {
		return profiles.getProfileByEmail(email);
	}
	public List<Profile> getSomeProfiles() {
		return profiles.getSomeProfiles();
	}
	public List<Job> getMyPostedJobs(String email) {
		return jobs.getMyPostedJobs(email);
	}
	public Job removeMyPostedJob(int id) {
		return jobs.removeMyPostedJob(id);
		
	}
	public List<Job> searchJobsByAllSearchMethods(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByAllSearchMethods(jobSearchMethod);
	}
	public List<Job> searchJobsByZipCode(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByZipCode(jobSearchMethod);
	}
	public List<Job> searchJobsByTitle(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByTitle(jobSearchMethod);
	}
	public List<Job> searchJobsByType(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByType(jobSearchMethod);
	}
	public List<Job> searchJobsByState(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByState(jobSearchMethod);
	}
	public List<Job> searchJobsByCity(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByCity(jobSearchMethod);
	}
	public List<Job> searchJobsCompanyName(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsCompanyName(jobSearchMethod);
	}
	public List<Job> searchJobsByEmail(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByEmail(jobSearchMethod);
	}
	public List<Job> searchJobsByCityStateZipCode(JobSearchMethod jobSearchMethod){
		return jobs.searchJobsByCityStateZipCode(jobSearchMethod);
	}

	public List<Profile> searchProfilesByAllSearchMethods(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByAllSearchMethods(profileSearchMethod);
	}
	
	public List<Profile> searchProfilesByZipCode(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByZipCode(profileSearchMethod);
	}
	public List<Profile> searchProfilesByState(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByState(profileSearchMethod);
	}
	public List<Profile> searchProfilesByCity(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByCity(profileSearchMethod);
	}
	public List<Profile> searchProfilesCompanyName(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesCompanyName(profileSearchMethod);
	}
	public List<Profile> searchProfilesByLastName(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByLastName(profileSearchMethod);
	}
	public List<Profile> searchProfilesByFirstName(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByFirstName(profileSearchMethod);
	}
	public List<Profile> searchProfilesByEmail(ProfileSearchMethod profileSearchMethod){
		return profiles.searchProfilesByEmail(profileSearchMethod);
	}
	public WorkExperience addWorkExperience(WorkExperience workExperience) {
		return profiles.addWorkExperience(workExperience);
	}
	public List<WorkExperience> getWorkExperiences(String email) {
		return profiles.getWorkExperiences(email);
	}
	public Profile updateFirstName(Profile profile) {
		return profiles.updateFirstName(profile);
	}
	public Profile updateLastName(Profile profile) {
		return profiles.updateLastName(profile);
	}
	public Profile updateBiography(Profile profile) {
		return profiles.updateBiography(profile);
	}
	public Profile updateJobTitle1(Profile profile) {
		return profiles.updateJobTitle(profile.getJobtitle1(), profile.getEmail());
	}
	public Profile updateJobTitle2(Profile profile) {
		return profiles.updateJobTitle(profile.getJobtitle2(), profile.getEmail());
	}
	public Profile updateJobTitle3(Profile profile) {
		return profiles.updateJobTitle(profile.getJobtitle3(), profile.getEmail());
	}
	public Profile updatePhone1(Profile profile) {
		return profiles.updatePhone(profile.getPhone1(), profile.getEmail());
	}
	public Profile updatePhone2(Profile profile) {
		return profiles.updatePhone(profile.getPhone2(), profile.getEmail());
	}
	public Profile updatePhone3(Profile profile) {
		return profiles.updatePhone(profile.getPhone3(), profile.getEmail());
	}
	public Profile updateCity1(Profile profile) {
		return profiles.updateCity1(profile);
	}
	public Profile updateState1(Profile profile) {
		return profiles.updateState1(profile);
	}
	public Profile updateZipCode1(Profile profile) {
		return profiles.updateZipCode1(profile);
	}
	public Profile updateGraduationYear(Profile profile) {
		return profiles.updateGraduationYear(profile);
	}
	public Profile updateSkill1(Profile profile) {
		return profiles.updateSkill(profile.getSkill1(), profile.getEmail());
	}
	public Profile updateSkill2(Profile profile) {
		return profiles.updateSkill(profile.getSkill2(), profile.getEmail());
	}
	public Profile updateSkill3(Profile profile) {
		return profiles.updateSkill(profile.getSkill3(), profile.getEmail());
	}
	public Profile updateSkill4(Profile profile) {
		return profiles.updateSkill(profile.getSkill4(), profile.getEmail());
	}
	public Profile updateSkill5(Profile profile) {
		return profiles.updateSkill(profile.getSkill5(), profile.getEmail());
	}
	public Profile updateAllSkills(Profile profile) {
		profiles.updateSkill(profile.getSkill1(), profile.getEmail());
		profiles.updateSkill(profile.getSkill2(), profile.getEmail());
		profiles.updateSkill(profile.getSkill3(), profile.getEmail());
		profiles.updateSkill(profile.getSkill4(), profile.getEmail());
		profiles.updateSkill(profile.getSkill5(), profile.getEmail());
		return profile;
	}
	public Profile updateAllJobTitles(Profile profile) {
		profiles.updateJobTitle(profile.getJobtitle1(), profile.getEmail());
		profiles.updateJobTitle(profile.getJobtitle2(), profile.getEmail());
		profiles.updateJobTitle(profile.getJobtitle3(), profile.getEmail());
		return profile;
	}
	public Profile updateAllPhones(Profile profile) {
		profiles.updatePhone(profile.getPhone1(), profile.getEmail());
		profiles.updatePhone(profile.getPhone2(), profile.getEmail());
		profiles.updatePhone(profile.getPhone3(), profile.getEmail());
		return profile;
	}
}
