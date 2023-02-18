package com.example.PFEproject;

import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.rest.UserREST;
import com.example.PFEproject.service.RoleService;
import com.example.PFEproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
@SpringBootApplication
public class PfEprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfEprojectApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserREST userREST, UserService userService, RoleService roleService){
		return args -> {
			roleService.saveRole(new Role(null, "ROLE_PILOTE"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_RESPONSABLE"));
//			userREST.SaveUser(new User(null,"john","john","john@cgi.com","john", null));
//			userREST.SaveUser(new User(null,"jj","jj","hajar.kati","hajar", null));
//			userService.addRoleToUser("hajar.kati","ROLE_ADMIN");
//			userService.addRoleToUser("john@cgi.com","ROLE_PILOTE");
		};
	}
}
