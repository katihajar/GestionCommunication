package com.example.PFEproject;

import com.example.PFEproject.bean.Role;
import com.example.PFEproject.rest.admin.UserREST;
import com.example.PFEproject.service.RoleService;
import com.example.PFEproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;
import java.util.TimeZone;
@SpringBootApplication
public class PfEprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfEprojectApplication.class, args);
	}
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Africa/Casablanca"));
	}
	@Bean
	CommandLineRunner run(UserREST userREST, UserService userService, RoleService roleService){
		return args -> {
			roleService.saveRole(new Role(1L, "ROLE_PILOTE"));
			roleService.saveRole(new Role(2L, "ROLE_ADMIN"));
			roleService.saveRole(new Role(3L, "ROLE_RESPONSABLE"));
			roleService.saveRole(new Role(4L, "ROLE_SUPERADMIN"));
		};
	}
}
