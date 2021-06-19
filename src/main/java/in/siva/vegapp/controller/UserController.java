package in.siva.vegapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.exception.UserRepeatedException;
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

	@PatchMapping("edit/name")
	public String updateName(@RequestParam("username") String username, @RequestParam("newName") String newName) {
		String message = "Name updated successfully";
		boolean isNameUpdated = userService.updateName(username, newName);
		if (!isNameUpdated) {
			message = "User not found";
		}
		return message;
	}

	@PatchMapping("edit/mobile_no")
	public String updateMobileNo(@RequestParam("username") String username,
			@RequestParam("newMobileNo") Long newMobileNo) {
		String message = "Mobile Number updated successfully";
		try {
			boolean isMobileUpdated = userService.updateMobileNo(username, newMobileNo);
			if (!isMobileUpdated) {
				message = "User not found";
			}
		} catch (UserRepeatedException e) {
			message = e.getMessage();
		}
		return message;
	}
	
	@PatchMapping("edit/email")
	public String updateEmail(@RequestParam("username") String username,
			@RequestParam("email") String newEmail) {
		String message = "Email ID updated successfully";
		try {
			boolean isMobileUpdated = userService.updateEmail(username, newEmail);
			if (!isMobileUpdated) {
				message = "User not found";
			}
		} catch (UserRepeatedException e) {
			message = e.getMessage();
		}
		return message;
	}
}
