package com.ipen.voting.services;

import com.ipen.voting.entities.Role;

public interface RoleService {

	Role findOrCreateRole(String name);

}
