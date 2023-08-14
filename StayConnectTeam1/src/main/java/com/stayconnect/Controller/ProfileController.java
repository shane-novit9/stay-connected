package com.stayconnect.Controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stayconnect.Service.AppService;
import com.stayconnect.model.Job;
import com.stayconnect.model.Profile;
import com.stayconnect.model.ProfileSearchMethod;
import com.stayconnect.model.WorkExperience;


@Controller
public class ProfileController {
	@Autowired 
	private AppService service;
	@RequestMapping(value="/add/profile", method=RequestMethod.GET)
	public String UserProfile(Model model) {
		model.addAttribute("profile", new Profile());
		return "NewProfile";
	}
	@RequestMapping(value="/add/profile", method=RequestMethod.POST)
	public String createNewUser(@Valid Profile profile, BindingResult result, Principal principal, Model model) {
//		if(result.hasErrors()) {
//			return "NewProfile"; 
//		}
		service.updateProfile(profile, principal.getName());
		return "redirect:/view/profile";
	}
	@RequestMapping(value="/add/workexperience", method=RequestMethod.GET)
	public String workExperience(Model model) {
		model.addAttribute("workExperience", new WorkExperience());
		return "WorkExperience";
	}
	@RequestMapping(value="/add/workexperience", method=RequestMethod.POST)
	public String addWorkExperience(@Valid WorkExperience workExperience, BindingResult result, Principal principal, Model model) {
		if(result.hasErrors()) {
			return "WorkExperience"; 
		}
		workExperience.setEmail(principal.getName());
        service.addWorkExperience(workExperience);
        
		return "redirect:/view/profile";
	}
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getUserHome(Principal principal, Model model) {
		model.addAttribute("jobs", getFiveMostRecentJobPost());
		model.addAttribute("profile", getProfileByEmail(principal.getName()));
		return "Homepage";
	}
	@RequestMapping(value="/view/profile", method=RequestMethod.GET)
	public String viewProfile(Principal principal, Model model) {
		model.addAttribute("profile", getProfileByEmail(principal.getName()));
		model.addAttribute("workExperiences", getWorkExperiences(principal.getName()));
		return "ProfileView";
	}

	@RequestMapping(value="/view/search/profiledetails", method=RequestMethod.GET)
	public String viewProfileDetails(@RequestParam(name="email", required=false) String email, Model model) {
		model.addAttribute("profile",getProfileByEmail(email));
		return "SearchProfileDetails";
	}
	
	@RequestMapping(value="/search/profiles", method=RequestMethod.GET)
	public String viewStulumniProfiles(Model model) {
		List<Profile> someProfiles = service.getSomeProfiles();
		model.addAttribute("profiles", someProfiles);
		model.addAttribute("searchMethod", new ProfileSearchMethod());
		return "ProfileSearch";
	}
	
	@RequestMapping(value="/search/profiles", method=RequestMethod.POST)
	public String submitProfileSearchMethod(ProfileSearchMethod profileSearchMethod, Model model) {
		List<Profile> profileSearchResult = service.searchProfiles(profileSearchMethod);
		model.addAttribute("profiles", profileSearchResult);
		model.addAttribute("searchMethod", new ProfileSearchMethod());
		return "ProfileSearch";
	}
	
	private List<Job> getFiveMostRecentJobPost() {
		return service.getFiveMostRecentJobPost();
	}
	private Profile getProfileByEmail(String name) {
		return service.getProfileByEmail(name);
	}
	private List<WorkExperience> getWorkExperiences(String email){
		return service.getWorkExperiences(email);
	}
}
