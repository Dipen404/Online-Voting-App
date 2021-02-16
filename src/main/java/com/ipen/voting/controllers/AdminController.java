package com.ipen.voting.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipen.voting.entities.Role;
import com.ipen.voting.entities.User;
import com.ipen.voting.entities.UserRole;
import com.ipen.voting.models.UserDto;
import com.ipen.voting.models.UserType;
import com.ipen.voting.services.RoleService;
import com.ipen.voting.services.UserService;
import com.ipen.voting.utils.AuthenticationUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
	@GetMapping("/profile")
	public String getUserHomePage(Model model) {
		String username = AuthenticationUtil.getLoggedInUser();
		model.addAttribute("user", username);
		return "admin/home";
	}

	@GetMapping("/add-admin")
	public String getAddAdminPage() {
		return "admin/addadmin";
	}
	
	@PostMapping("/add-admin")
	public String postAdminCreate(@ModelAttribute UserDto userDto) throws IOException {
	
	Role role = roleService.findOrCreateRole("ROLE_ADMIN");
	Set<UserRole> userRole = new HashSet<UserRole>();
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
			userDto.getFirstName(), userDto.getLastName(),UserType.ADMIN);
	userRole.add(new UserRole(user, role));
	userService.createUser(user, userRole);
	return "redirect:/admin/profile";
		
	}
}