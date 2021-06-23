package in.siva.vegapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.exception.UserRepeatedException;
import in.siva.vegapp.model.User;
import in.siva.vegapp.validator.UtilValidator;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UtilValidator utilValidator;

	/**
	 * This method is used to register a user.
	 * It will validate whether user's details were already registered by a user or not.
	 * If no details were used by a user, then user registration will be successful
	 * @param user
	 */
	public void registerUser(User user) {
		if (utilValidator.isUserNotRepeated(user)) {
			userRepo.save(user);
		}
	}

	/**
	 * This method is used to validate a user's login.
	 * If given username & password is valid in DB then id will be returned.
	 * Else id will be null depending on these info login is validated.
	 * @param username
	 * @param password
	 * @return
	 */
	public String loginValidation(String username, String password) {
		return userRepo.findRoleOfUser(username, password);
	}

	/**
	 * This method is used to validate name update.
	 * It will update name of a user where username present
	 * @param username
	 * @param newName
	 * @return
	 */
	public boolean updateName(String username, String newName) {
		boolean isNameUpdated = true;
		Long userId = userRepo.findUserId(username);
		Long updatedRowId = userRepo.updateNameById(newName, userId);
		if (updatedRowId == null) {
			isNameUpdated = false;
		}
		return isNameUpdated;
	}

	/**
	 * This method is used to update mobile number of a user.
	 * It will update mobile number where username is equal
	 * @param username
	 * @param mobileNo
	 * @return
	 */
	public boolean updateMobileNo(String username, Long mobileNo) {
		boolean isMobileUpdated = true;
		Long userId = userRepo.findUserId(username);
		if(utilValidator.isMobileNoRepeated(mobileNo)){
			throw new UserRepeatedException("Mobile Number already used by a user");
		}
		Long updatedRowId = userRepo.updateMobileNoById(mobileNo, userId);
		if (updatedRowId == null) {
			isMobileUpdated = false;
		}
		return isMobileUpdated;
	}
	
	/**
	 * This method will update email ID where username matched
	 * @param username
	 * @param email
	 * @return
	 */
	public boolean updateEmail(String username, String email) {
		boolean isEmailUpdated = true;
		Long userId = userRepo.findUserId(username);
		if(utilValidator.isEmailRepeated(email)){
			throw new UserRepeatedException("Email ID already used by a user");
		}
		Long updatedRowId = userRepo.updateEmailById(email, userId);
		if (updatedRowId == null) {
			isEmailUpdated = false;
		}
		return isEmailUpdated;
	}
}
