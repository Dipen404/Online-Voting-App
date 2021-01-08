package com.ipen.voting.services;

import java.io.IOException;
import java.util.Set;

import com.ipen.voting.entities.User;
import com.ipen.voting.entities.UserRole;

public interface UserService {
	
	void createUser(User user, Set<UserRole> userRoles) throws IOException;

}
