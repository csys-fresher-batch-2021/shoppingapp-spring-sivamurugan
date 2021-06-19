package in.siva.vegapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.dto.Message;
import in.siva.vegapp.dto.UserInfo;
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

	private static final String INVALID_USER = "User not found";

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
	public ResponseEntity<?> register(@RequestBody UserDetail user) {
		try {
			userService.registerUser(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setErrorMessage("Registration failed");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
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
	public ResponseEntity<?> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		String role = userService.loginValidation(username, password);
		Message message = new Message();
		UserInfo userInfo = new UserInfo();
		if (role != null) {
			message.setInfoMessage("Login Successful");
			userInfo.setRole(role);
			message.setUserInfo(userInfo);
		} else {
			message.setErrorMessage("Login Failed");
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
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
	public ResponseEntity<?> updateName(@RequestParam("username") String username,
			@RequestParam("newName") String newName) {
		boolean isNameUpdated = userService.updateName(username, newName);
		Message message = new Message();
		if (!isNameUpdated) {
			message.setErrorMessage(INVALID_USER);
		} else {
			message.setInfoMessage("Name Updated Successfully");
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
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
	public ResponseEntity<?> updateMobileNo(@RequestParam("username") String username,
			@RequestParam("newMobileNo") Long newMobileNo) {
		Message message = new Message();
		try {
			boolean isMobileUpdated = userService.updateMobileNo(username, newMobileNo);
			if (!isMobileUpdated) {
				message.setErrorMessage(INVALID_USER);
			} else {
				message.setInfoMessage("Mobile Number updated successfully");
			}
		} catch (UserRepeatedException e) {
			message.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * This method is used to update email of a user. This method will update
	 * email ID of a username. If email is repeated or not a valid
	 * email or username not valid then error message will be given as response.
	 * Else positive response will be given
	 * @param username
	 * @param newEmail
	 * @return
	 */
	@PatchMapping("edit/email")
	public ResponseEntity<?> updateEmail(@RequestParam("username") String username,
			@RequestParam("email") String newEmail) {
		Message message = new Message();
		try {
			boolean isEmailUpdated = userService.updateEmail(username, newEmail);
			if (!isEmailUpdated) {
				message.setErrorMessage(INVALID_USER);
			} else {
				message.setInfoMessage("Email ID updated successfully");
			}
		} catch (UserRepeatedException e) {
			message.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
