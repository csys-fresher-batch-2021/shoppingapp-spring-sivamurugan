package in.siva.vegapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.model.UserDetail;

@RestController
@RequestMapping("vegapp/v1/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("register")
	public String register(@RequestBody UserDetail user) {
		String message = "Successfully registered";
		try {
			userRepo.save(user);
		} catch(Exception e) {
			message = "Registration failed";
		}
		return message;
	}
}
