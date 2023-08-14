package com.stayconnect.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stayconnect.Database.AppRepository;
import com.stayconnect.Service.AppService;
import com.stayconnect.model.Job;
import com.stayconnect.model.JobSearchMethod;
import com.stayconnect.model.Profile;
import com.stayconnect.model.ProfileSearchMethod;
import com.stayconnect.model.UserAccount;
import com.stayconnect.model.WorkExperience;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
	private AppRepository repository;
	@Override
	public UserAccount addAccount(UserAccount account) {
		return repository.addAccount(account);
	}

	@Override
	public UserAccount getAccountByEmail(String email) {
		return repository.getAccountByEmail(email);
	}

	@Override
	public UserAccount deleteAccount(UserAccount account) {	
		return repository.deleteAccount(account); 
	}
	
	public Job getJobByEmail(String email) {
		return repository.getJobByEmail(email);
	}
	@Override
	public Job addJob(Job job) {
		return repository.addJob(job);
	}
	@Override
	public List<String> getAuthorities() {
		return repository.getAuthorities();
	}

	@Override
	public void updateActive(String email, boolean active) {
		repository.updateActive(email, active);
	}

	@Override
	public Job getJobById(int id) {
		return repository.getJobById(id);
	}

	@Override
	public Profile addProfile(Profile p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile updateProfile(Profile p, String email) {
		String empty = "";
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				   p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				   p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				   p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				   p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return null;
		}
		p.setEmail(email);
		if(p.getFirstName() != empty && p.getLastName() == empty && p.getBiography() == empty &&
		   p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
		   p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
		   p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
		   p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateFirstName(p);
		}
		if(p.getFirstName() == empty && p.getLastName() != empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateLastName(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() != empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateBiography(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() != empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateJobTitle1(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() != empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateJobTitle2(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() != empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() != empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateJobTitle3(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() != empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updatePhone1(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() != empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updatePhone2(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() != empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updatePhone3(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() != empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateCity1(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() != empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateState1(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() != empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateZipCode1(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() != empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateGraduationYear(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() != empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateSkill1(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() != empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateSkill2(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() != empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateSkill3(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() != empty && p.getSkill5() == empty) {
			return repository.updateSkill4(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() != empty) {
			return repository.updateSkill5(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty || 
				p.getSkill2() == empty || p.getSkill3() == empty || p.getSkill4() == empty || p.getSkill5() != empty) {
			return repository.updateAllSkills(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() != empty || p.getJobtitle2() != empty || p.getJobtitle3() != empty &&
				p.getPhone1() == empty && p.getPhone2() == empty && p.getPhone3() == empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateAllJobTitles(p);
		}
		if(p.getFirstName() == empty && p.getLastName() == empty && p.getBiography() == empty &&
				p.getJobtitle1() == empty && p.getJobtitle2() == empty && p.getJobtitle3() == empty &&
				p.getPhone1() != empty || p.getPhone2() != empty || p.getPhone3() != empty && p.getCity1() == empty &&
				p.getState1() == empty && p.getZipcode1() == empty && p.getGraduationYear() == empty && p.getSkill1() == empty && 
				p.getSkill2() == empty && p.getSkill3() == empty && p.getSkill4() == empty && p.getSkill5() == empty) {
			return repository.updateAllPhones(p);
		}
		return repository.updateProfile(p, email);
	}

	@Override
	public Profile getProfileByEmail(String email) {
		return repository.getProfileByEmail(email);
	}

	@Override
	public List<Job> getFiveMostRecentJobPost() {
		return repository.getFiveMostRecentJobPost();
	}

	@Override
	public List<Job> getMyPostedJobs(String email) {
		return repository.getMyPostedJobs(email);
	}

	@Override
	public Job removeMyPostedJob(int id) {
		return repository.removeMyPostedJob(id);
	}

	@Override
	public List<Profile> getSomeProfiles() {
		return repository.getSomeProfiles();
	}

	@Override
	public List<Profile> searchProfiles(ProfileSearchMethod profileSearchMethod) {
		String empty = "";
		if(profileSearchMethod.getEmail() == empty && profileSearchMethod.getFirstName() == empty && profileSearchMethod.getLastName() == empty &&
				   profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getCity() == empty &&
				   profileSearchMethod.getState() == empty && profileSearchMethod.getZip() == empty) {
			return null;
		}
		if(profileSearchMethod.getFirstName() == empty && profileSearchMethod.getLastName() == empty &&
		   profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getCity() == empty &&
		   profileSearchMethod.getState() == empty && profileSearchMethod.getZip() == empty) {
			return repository.searchProfilesByEmail(profileSearchMethod);
		}
		if(profileSearchMethod.getEmail() == empty && profileSearchMethod.getLastName() == empty &&
				profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getCity() == empty &&
				profileSearchMethod.getState() == empty && profileSearchMethod.getZip() == empty) {
			return repository.searchProfilesByFirstName(profileSearchMethod);
		}
		if(profileSearchMethod.getFirstName() == empty && profileSearchMethod.getEmail() == empty &&
				profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getCity() == empty &&
				profileSearchMethod.getState() == empty && profileSearchMethod.getZip() == empty) {
			return repository.searchProfilesByLastName(profileSearchMethod);
		}
		if(profileSearchMethod.getFirstName() == empty && profileSearchMethod.getLastName() == empty &&
				profileSearchMethod.getEmail() == empty && profileSearchMethod.getCity() == empty &&
				profileSearchMethod.getState() == empty && profileSearchMethod.getZip() == empty) {
			return repository.searchProfilesCompanyName(profileSearchMethod);
		}
		if(profileSearchMethod.getFirstName() == empty && profileSearchMethod.getLastName() == empty &&
				profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getEmail() == empty &&
				profileSearchMethod.getState() == empty && profileSearchMethod.getZip() == empty) {
			return repository.searchProfilesByCity(profileSearchMethod);
		}
		if(profileSearchMethod.getFirstName() == empty && profileSearchMethod.getLastName() == empty &&
				profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getCity() == empty &&
				profileSearchMethod.getEmail() == empty && profileSearchMethod.getZip() == empty) {
			return repository.searchProfilesByState(profileSearchMethod);
		}
		if(profileSearchMethod.getFirstName() == empty && profileSearchMethod.getLastName() == empty &&
				profileSearchMethod.getCompanyName() == empty && profileSearchMethod.getCity() == empty &&
				profileSearchMethod.getState() == empty && profileSearchMethod.getEmail() == empty) {
			return repository.searchProfilesByZipCode(profileSearchMethod);
		}

	   return repository.searchProfilesByAllSearchMethods(profileSearchMethod);
	}

	@Override
	public List<Job> searchJobs(JobSearchMethod jobSearchMethod) {
		String empty = "";
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getState() == empty && jobSearchMethod.getZip() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return null;
		}
		if(jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getState() == empty && jobSearchMethod.getZip() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return repository.searchJobsByEmail(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getState() == empty && jobSearchMethod.getZip() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return repository.searchJobsByTitle(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getState() == empty && jobSearchMethod.getZip() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return repository.searchJobsByType(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && 
		   jobSearchMethod.getState() == empty && jobSearchMethod.getZip() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return repository.searchJobsByCity(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getZip() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return repository.searchJobsByState(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getState() == empty && 
		   jobSearchMethod.getCompanyName() == empty) {
			return repository.searchJobsByZipCode(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCity() == empty &&
		   jobSearchMethod.getState() == empty && jobSearchMethod.getZip() == empty) {
			return repository.searchJobsCompanyName(jobSearchMethod);
		}
		if(jobSearchMethod.getEmail() == empty && jobSearchMethod.getTitle() == empty &&
		   jobSearchMethod.getType() == empty && jobSearchMethod.getCompanyName() == empty &&
		   jobSearchMethod.getCity() != empty && jobSearchMethod.getState() != empty &&
		   jobSearchMethod.getZip() != empty) {
			return repository.searchJobsByCityStateZipCode(jobSearchMethod);
		}
		return repository.searchJobsByAllSearchMethods(jobSearchMethod);
	}

	@Override
	public WorkExperience addWorkExperience(WorkExperience workExperience) {
		return repository.addWorkExperience(workExperience);
	}

	@Override
	public List<WorkExperience> getWorkExperiences(String email) {
		return repository.getWorkExperiences(email);
	}
}
