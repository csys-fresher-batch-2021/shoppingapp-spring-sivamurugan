package in.siva.vegapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.dto.UserInfo;
import in.siva.vegapp.exception.InvalidLoginException;
import in.siva.vegapp.exception.UsernameAlreadyExistException;
import in.siva.vegapp.model.User;
import in.siva.vegapp.validator.UserValidator;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserValidator userValidator;

	/**
	 * This method is used to register a user. It will validate whether user's
	 * details were already registered by a user or not. If no details were used by
	 * a user, then user registration will be successful
	 * 
	 * @param user
	 * @return
	 */
	public boolean registerUser(User user) {
		boolean isRegistered = false;
		if (userValidator.isUserNotRepeated(user)) {
			userRepo.save(user);
			isRegistered = true;
		}
		return isRegistered;
	}

	/**
	 * This method is used to validate a user's login. If given username & password
	 * is valid in DB then id will be returned. Else id will be null depending on
	 * these info login is validated.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserInfo loginValidation(String username, String password) {
		UserInfo user = userRepo.findUserInfo(username, password);
		if (user == null) {
			throw new InvalidLoginException("Invalid Login Credentials");
		}
		return user;
	}

	/**
	 * This method is used to validate name update. It will update name of a user
	 * where username present
	 * 
	 * @param username
	 * @param newName
	 * @return
	 */
	public boolean updateName(String username, String newName) {
		boolean isNameUpdated = true;
		Long userId = userRepo.findUserId(username);
		Integer updatedRow = userRepo.updateNameById(newName, userId);
		if (updatedRow == 0) {
			isNameUpdated = false;
		}
		return isNameUpdated;
	}

	/**
	 * This method is used to update mobile number of a user. It will update mobile
	 * number where username is equal
	 * 
	 * @param username
	 * @param mobileNo
	 * @return
	 */
	public boolean updateMobileNo(String username, Long mobileNo) {
		boolean isMobileUpdated = true;
		Long userId = userRepo.findUserId(username);
		if (userValidator.isMobileNoRepeated(mobileNo)) {
			throw new UsernameAlreadyExistException("Mobile Number already used by a user");
		}
		Integer updatedRow = userRepo.updateMobileNoById(mobileNo, userId);
		if (updatedRow == 0) {
			isMobileUpdated = false;
		}
		return isMobileUpdated;
	}

	/**
	 * This method will update email ID where username matched
	 * 
	 * @param username
	 * @param email
	 * @return
	 */
	public boolean updateEmail(String username, String email) {
		boolean isEmailUpdated = true;
		Long userId = userRepo.findUserId(username);
		if (userValidator.isEmailRepeated(email)) {
			throw new UsernameAlreadyExistException("Email ID already used by a user");
		}
		Integer updatedRow = userRepo.updateEmailById(email, userId);
		if (updatedRow == 0) {
			isEmailUpdated = false;
		}
		return isEmailUpdated;
	}
}
