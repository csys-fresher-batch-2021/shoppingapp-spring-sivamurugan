package in.siva.vegapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.model.UserDetail;
import in.siva.vegapp.service.UserService;

@RestController
@RequestMapping("vegapp/v1/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;

	@PostMapping("register")
	public String register(@RequestBody UserDetail user) {
		String message = "Successfully registered";
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			message = "Registration failed";
		}
		return message;
	}

	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		String message = "Login successful";
		String role = userService.loginValidation(username, password);
		if (role != null) {
			message += " And role is : " + role;
		} else {
			message = "Login failed";
		}
		return message;
	}
}
