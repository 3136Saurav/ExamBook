package com.exam.exambook;

import com.exam.exambook.model.Role;
import com.exam.exambook.model.User;
import com.exam.exambook.model.UserRole;
import com.exam.exambook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExambookApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExambookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("===================== Starting Exam Book =====================");

//		User user = new User();
//
//		user.setFirstName("Drake");
//		user.setLastName("Parker");
//		user.setUsername("drake12");
//		user.setPassword("drake123");
//		user.setEmail("drake@parker.com");
//		user.setPhone("+1-5546323210");
//		user.setProfile("default.png");
//
//		Role role1 = new Role();
//		role1.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//		userRoleSet.add(userRole);
//
//		User user1 = userService.createUser(user, userRoleSet);
//		System.out.println(user1);
	}
}
