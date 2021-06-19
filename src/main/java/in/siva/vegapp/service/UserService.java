package in.siva.vegapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.exception.UserRepeatedException;
import in.siva.vegapp.model.UserDetail;
import in.siva.vegapp.validator.UtilValidator;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UtilValidator utilValidator;

	public void registerUser(UserDetail user) {
		if (utilValidator.isUserNotRepeated(user)) {
			userRepo.save(user);
		}
	}

	public String loginValidation(String username, String password) {
		return userRepo.findRoleOfUser(username, password);
	}

	public boolean updateName(String username, String newName) {
		boolean isNameUpdated = true;
		Long userId = userRepo.findUserId(username);
		Long updatedRowId = userRepo.updateNameById(newName, userId);
		if (updatedRowId == null) {
			isNameUpdated = false;
		}
		return isNameUpdated;
	}

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
