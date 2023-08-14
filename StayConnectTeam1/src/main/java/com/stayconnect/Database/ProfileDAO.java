package com.stayconnect.Database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stayconnect.model.Profile;
import com.stayconnect.model.ProfileSearchMethod;
import com.stayconnect.model.WorkExperience;

@Repository
public interface ProfileDAO {
	public Profile addProfile(Profile p);
	public Profile updateProfile(Profile p,String email);
	public Profile deleteProfile(Profile p);
	public Profile getProfiles(ProfileSearchMethod method);
	public Profile getProfileByEmail(String email);
	public List<Profile> getSomeProfiles();
	public List<Profile> searchProfilesByAllSearchMethods(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesByZipCode(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesByState(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesByCity(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesCompanyName(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesByLastName(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesByFirstName(ProfileSearchMethod profileSearchMethod);
	public List<Profile> searchProfilesByEmail(ProfileSearchMethod profileSearchMethod);
	public WorkExperience addWorkExperience(WorkExperience workExperience);
	public List<WorkExperience> getWorkExperiences(String email);
	public Profile updateFirstName(Profile profile);
	public Profile updateLastName(Profile profile);
	public Profile updateBiography(Profile profile);
	public Profile updateJobTitle(String jobtitle, String email);
	public Profile updatePhone(String phone, String email);
	public Profile updateCity1(Profile profile);
	public Profile updateState1(Profile profile);
	public Profile updateZipCode1(Profile profile);
	public Profile updateGraduationYear(Profile profile);
	public Profile updateSkill(String skill, String email);
}
