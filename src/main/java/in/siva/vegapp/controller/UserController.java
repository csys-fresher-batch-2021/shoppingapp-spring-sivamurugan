package in.siva.vegapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.dto.UserInfo;
import in.siva.vegapp.exception.InvalidInputException;
import in.siva.vegapp.model.User;
import in.siva.vegapp.service.UserService;

@RestController
@RequestMapping("vegapp/v1/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;

	/**
	 * This method is used to register a user. It takes user's details and send it
	 * to service layer. If the response from service layer is positive then the
	 * user registration is successful. Else error message will be given as
	 * response.
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("register")
	public boolean register(@Valid @RequestBody User user) {
		return userService.registerUser(user);
	}

	/**
	 * This method is used to validate user's login. If the given username &
	 * password is valid then 'Login Successful' message is given as response. Else
	 * 'Login failed' message is given as response.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("login")
	public UserInfo login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.loginValidation(username, password);
	}

	/**
	 * This method is used to update name of a user. It will check whether the
	 * username from front-end is valid or not. If valid then name is updated. Else
	 * error message will be given as response.
	 * 
	 * @param username
	 * @param newName
	 * @return
	 */
	@PatchMapping("edit/name")
	public boolean updateName(@RequestParam("username") String username, @RequestParam("newName") String newName) {
		return userService.updateName(username, newName);
	}

	/**
	 * This method is used to update mobile number of a user This method will update
	 * mobile number of a username. If mobile number is repeated or not a valid
	 * number or username not valid then error message will be given as response.
	 * Else positive response will be given
	 * 
	 * @param username
	 * @param newMobileNo
	 * @return
	 */
	@PatchMapping("edit/mobile_no")
	public boolean updateMobileNo(@RequestParam("username") String username,
			@RequestParam("newMobileNo") Long newMobileNo) {
		if (newMobileNo.toString().length() == 10) {
			return userService.updateMobileNo(username, newMobileNo);
		} else {
			throw new InvalidInputException("Please enter valid mobile number");
		}
	}

	/**
	 * This method is used to update email of a user. This method will update email
	 * ID of a username. If email is repeated or not a valid email or username not
	 * valid then error message will be given as response. Else positive response
	 * will be given
	 * 
	 * @param username
	 * @param newEmail
	 * @return
	 */
	@PatchMapping("edit/email")
	public boolean updateEmail(@RequestParam("username") String username, @RequestParam("email") String newEmail) {
		return userService.updateEmail(username, newEmail);
	}
}
