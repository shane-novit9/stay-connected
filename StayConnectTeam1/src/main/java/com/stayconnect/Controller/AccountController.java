package com.stayconnect.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stayconnect.security.ActivationCode;
import com.stayconnect.security.WebAccount;
import com.stayconnect.model.Profile;
import com.stayconnect.model.UserAccount;
import com.stayconnect.security.AccountValidator;
import com.stayconnect.Service.AppService;


@Controller
public class AccountController {
	@Autowired
	private AppService service;
	
	@Autowired 
	PasswordEncoder passEncoder;
	
	@Autowired
	AccountValidator validator;
	
	@RequestMapping(value="/webhome", method=RequestMethod.GET)
	public String webHome(Model model) {
		return "WebHome";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String registerUser(Model model) {
		model.addAttribute("webAccount", new WebAccount());
		model.addAttribute("authorities", service.getAuthorities());
		return "Registration";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String createNewUser(@Valid WebAccount webAccount, BindingResult result, Model model, final RedirectAttributes redirect) {
		validator.validate(webAccount, result);
		if(result.hasErrors()) {
			model.addAttribute("authorities", service.getAuthorities());
			return "Registration"; 
		}
		String encodedPswd = passEncoder.encode(webAccount.getPassword());
		Profile profile = new Profile();
		profile.setEmail(webAccount.getEmail());
		profile.setFirstName(webAccount.getFirstName());
		profile.setLastName(webAccount.getLastName());
		service.addAccount(new UserAccount(webAccount.getEmail(), encodedPswd,
				webAccount.getAuthority(), false,profile));
		
		redirect.addFlashAttribute("code", passEncoder.encode(webAccount.getEmail()));
		redirect.addFlashAttribute("email", webAccount.getEmail());	
		
		return "redirect:/account/activation";
	}
	

	@RequestMapping(value="/account/activation", method=RequestMethod.GET)
	public String displayActivateAccount(Model model, @ModelAttribute("code")final String actCode,
			@ModelAttribute("email")final String email) {
		model.addAttribute("realCode", actCode);
		ActivationCode activCode = new ActivationCode();
		activCode.setEmail(email);
		model.addAttribute("webCode", activCode);
		return "AccountActivation";
	}
	
	@RequestMapping(value="/account/activation", method=RequestMethod.POST)
	public String activateAccount(ActivationCode activationCode, @ModelAttribute("code")final String realCode, Model model) {
		if(!isValidCode(activationCode.getCode(), realCode)) {
			model.addAttribute("realCode", realCode);
			// FIX: Will not contain email for update
			ActivationCode activeCode= new ActivationCode();
			activeCode.setEmail(activationCode.getEmail());
			model.addAttribute("webCode", activeCode);
			return "AccountActivation";			
		}
		service.updateActive(activationCode.getEmail(), true);
		UserAccount userAccount = service.getAccountByEmail(activationCode.getEmail());
		List<String> roles = new ArrayList<>();
		roles.add(userAccount.getAuthority());
		autologin(userAccount,roles);
		return "redirect:/";
	}
	
	private boolean isValidCode(String enter, String actual) {
		return enter.equals(actual);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage() {
		return "Login";
	}
	
	@RequestMapping(value="/delete/account", method=RequestMethod.GET)
	public String displayDeleteConfirmation(@RequestParam(value="email", required=true)String email, Model model, Principal principal) {
		String principalAuthority = service.getAccountByEmail(principal.getName()).getAuthority();
		if(!principalAuthority.equals("ROLE_FACULTY")) return "ProfileSearch";
		
		UserAccount account = service.getAccountByEmail(email);
		model.addAttribute("UserAccount", account);
		return "ConfirmProfileDeletion";
	}
	
	@RequestMapping(value="/delete/account", method=RequestMethod.POST)
	public String deleteAccount(@Valid UserAccount account, Model model) {
		service.deleteAccount(account);
		return "redirect:/search/profiles";
	}
	
	private void autologin(UserAccount userAccount, List<String> roles) {
		Collection<GrantedAuthority> authorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(String.join(", ", roles));
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				userAccount.getEmail(), null, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
