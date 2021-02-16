package com.ipen.voting.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipen.voting.entities.User;
import com.ipen.voting.entities.UserRole;
import com.ipen.voting.repositories.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/allusers")
	public void getAllUsers() {
		List<User> users = userRepository.findAll();
		for(User u:users) {
			System.out.println("Username is "+u.getEmail());
			System.out.println("Authority is "+u.getAuthorities().toString());
			//System.out.println("Role is " +u.getUserRoles().toString());
			for(UserRole ur: u.getUserRoles()) {
				System.out.println("Role is "+ ur.getRole().getName());
			}
		}
		System.out.println(users);
	}

}
