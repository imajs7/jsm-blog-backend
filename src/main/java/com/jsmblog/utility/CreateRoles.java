package com.jsmblog.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.jsmblog.entity.Role;
import com.jsmblog.repository.RoleDao;

@Configuration
public class CreateRoles {
	
	@Autowired
	private RoleDao roleDao;
	
	public void addRolesToDB() {
		
		Role role1 = Role.builder().id(1).roleName("ROLE_SUPERADMIN").build();
		Role role2 = Role.builder().id(2).roleName("ROLE_ADMINISTRATOR").build();
		Role role3 = Role.builder().id(3).roleName("ROLE_EDITOR").build();
		Role role4 = Role.builder().id(4).roleName("ROLE_STORE_MANAGER").build();
		Role role5 = Role.builder().id(5).roleName("ROLE_AUTHOR").build();
		Role role6 = Role.builder().id(6).roleName("ROLE_CONTRIBUTOR").build();
		Role role7 = Role.builder().id(7).roleName("ROLE_CUSTOMER").build();
		Role role8 = Role.builder().id(8).roleName("ROLE_SUBSCRIBER").build();
		
		roleDao.saveAll(List.of(role1, role2, role3, role4, role5, role6, role7, role8));
	}

}
