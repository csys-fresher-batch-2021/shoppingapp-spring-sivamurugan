package in.siva.vegapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.exception.EmailAlreadyExistException;
import in.siva.vegapp.exception.MobileNumberAlreadyExistException;
import in.siva.vegapp.exception.UsernameAlreadyExistException;
import in.siva.vegapp.model.User;

@Component
public class UserValidator {

	@Autowired
	UserRepository userRepo;

	/**
	 * This method will validate whether the mobile, email, username entered by a
	 * user were already registered by a user or not. If repeated it will raise an
	 * exception
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserNotRepeated(User user) {
		boolean isNotExists = true;
		Iterable<User> userList = userRepo.findAll();
		for (User userDetail : userList) {
			if (userDetail.getEmail().equalsIgnoreCase(user.getEmail())) {
				throw new EmailAlreadyExistException("E_ER01");
			} else if (userDetail.getMobileNumber().equals(user.getMobileNumber())) {
				throw new MobileNumberAlreadyExistException("E_MR01");
			} else if (userDetail.getUsername().equals(user.getUsername())) {
				throw new UsernameAlreadyExistException("E_UR01");
			}
		}
		return isNotExists;
	}

	/**
	 * This method will validate whether the entered mobile number is registered or
	 * not
	 * 
	 * @param mobileNo
	 * @return
	 */
	public boolean isMobileNoRepeated(Long mobileNo) {
		boolean exists = true;
		Long id = userRepo.findIdByMobile(mobileNo);
		if (id == null) {
			exists = false;
		}
		return exists;
	}

	/**
	 * This method will validate whether the entered email ID is registered or not
	 * 
	 * @param email
	 * @return
	 */
	public boolean isEmailRepeated(String email) {
		boolean exists = true;
		Long id = userRepo.findIdByEmail(email);
		if (id == null) {
			exists = false;
		}
		return exists;
	}
}
