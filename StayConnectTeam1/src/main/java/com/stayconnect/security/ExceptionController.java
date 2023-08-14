package com.stayconnect.security;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("message", "Sorry " + username + 
							", you don't have privileges to view this page!!!");
		return "403";
	}
}
