package com.stayconnect.Controller;

import java.security.Principal;
import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stayconnect.Service.AppService;
import com.stayconnect.model.Job;
import com.stayconnect.model.JobSearchMethod;
import com.stayconnect.model.UserAccount;


@Controller
public class JobController {
	@Autowired 
	private AppService service;
	
	@RequestMapping(value="/add/job", method=RequestMethod.GET)
	public String addJobForm(Model model) {
		model.addAttribute("job", new Job());
		return "NewJob";
	}
	@RequestMapping(value="/add/job", method=RequestMethod.POST)
	public String addJob(@Valid Job job, BindingResult result, Principal principal, Model model) {
		if(result.hasErrors()) {
			return "NewJob";
		}
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Job Name = " + job.getTitle());

		Job jobb = new Job(
				job.getTitle(),
				job.getType(),
				job.getDescription(),
				job.getCompanyName(),
				principal.getName(),
				job.getSkill1(),
				job.getSkill2(),
				job.getSkill3(),
				job.getSkill4(),
				job.getSkill5(),
				job.getCity1(),
				job.getState1(),
				job.getZipcode1(),
				job.getCity2(),
				job.getState2(),
				job.getZipcode2(),
				job.getCity3(),
				job.getState3(),
				job.getZipcode3(),
				job.getCity4(),
				job.getState4(),
				job.getZipcode4()
			);
//		logger.info("Job Skill1 = " + jobb.getSkill1());
		service.addJob(jobb);
		return "redirect:/job/confirmation";
	}

	@RequestMapping(value="/job/confirmation", method=RequestMethod.GET)
	public String displayConfirmation(Principal principal, Model model) {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Principal = " + principal.getName());

		model.addAttribute("job", service.getJobByEmail(principal.getName()));
		return "PostJobConfirmation";
	}
	
	@RequestMapping(value="/search/jobs", method=RequestMethod.GET)
	public String jobSearch(Model model) {
		List<Job> someJobs = service.getFiveMostRecentJobPost();
		model.addAttribute("jobs", someJobs);
		model.addAttribute("searchMethod", new JobSearchMethod());
		return "JobSearch";
	}
	
	@RequestMapping(value="/search/jobs", method=RequestMethod.POST)
	public String displayDetails(JobSearchMethod method, Model model) {
		List<Job> jobSearchResult = service.searchJobs(method);
		model.addAttribute("jobs", jobSearchResult);
		model.addAttribute("searchMethod", new JobSearchMethod());
		return "JobSearch";
	}
	
	@RequestMapping(value="/JobDetails", method=RequestMethod.GET)
	public String viewHomeJobDetail(@RequestParam(name="id", required=false, defaultValue="1") int id, Model model) {
		model.addAttribute("job",getJobById(id));
		return "JobDetails";
	}
	@RequestMapping(value="/view/searched/JobDetails", method=RequestMethod.GET)
	public String viewSearchJobDetail(@RequestParam(name="id", required=false, defaultValue="1") int id, Model model) {
		model.addAttribute("job",getJobById(id));
		return "JobSearchDetails";
	}
	@RequestMapping(value="/view/mypostedjob/details", method=RequestMethod.GET)
	public String viewMyJobDetails(@RequestParam(name="id", required=false, defaultValue="1") int id, Model model) {
		model.addAttribute("job",getJobById(id));
		return "ViewMyPostedJobsDetails";
	}


	private Job getJobById(int id) {
		return service.getJobById(id);
	}
	@RequestMapping(value="/remove/mypostedjob", method=RequestMethod.GET)
	public String displayDeleteConfirmation(@RequestParam(value="id", required=true) int id, Model model, Principal principal) {		
		Job job = service.getJobById(id);
		model.addAttribute("job", job);
		return "ConfirmJobDeletion";
	}
	@RequestMapping(value="/remove/mypostedjob", method=RequestMethod.POST)
	public String removeMyPostedJob(@RequestParam(value="id", required=false) int id, Model model) {
		service.removeMyPostedJob(id);
		return "redirect:/view/myjobs";
	}

	@RequestMapping(value="/view/myjobs", method=RequestMethod.GET)
	public String getUserHome(Principal principal, Model model) {
		model.addAttribute("jobs",getMyPostedJobs(principal.getName()));
		return "ViewMyPostedJobs";
	}
	private List<Job> getMyPostedJobs(String email) {
		return service.getMyPostedJobs(email);
	}
}
