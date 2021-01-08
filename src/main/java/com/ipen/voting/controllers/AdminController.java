package com.ipen.voting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipen.voting.utils.AuthenticationUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/profile")
	public String getUserHomePage(Model model) {
		String username = AuthenticationUtil.getLoggedInUser();
		model.addAttribute("user", username);
		return "admin/home";
	}

}