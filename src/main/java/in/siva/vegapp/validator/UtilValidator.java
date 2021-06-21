package in.siva.vegapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.exception.UserRepeatedException;
import in.siva.vegapp.model.UserDetail;

@Component
public class UtilValidator {

	@Autowired
	UserRepository userRepo;

	/**
	 * This method will validate whether the mobile, email, username entered by a
	 * user were already registered by a user or not. If repeated it will raise an exception
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserNotRepeated(UserDetail user) {
		boolean isNotExists = true;
		Iterable<UserDetail> userList = userRepo.findAll();
		for (UserDetail userDetail : userList) {
			if (userDetail.getEmail().equalsIgnoreCase(user.getEmail())) {
				throw new UserRepeatedException("E_ER01");
			} else if (userDetail.getMobileNumber() == user.getMobileNumber()) {
				throw new UserRepeatedException("E_MR01");
			} else if (userDetail.getUsername().equals(user.getUsername())) {
				throw new UserRepeatedException("E_UR01");
			}
		}
		return isNotExists;
	}

	/**
	 * This method will validate whether the entered mobile number is registered or not
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
	
	/**
	 * This method is used to check the entered product has alphabets characters
	 * only If yes it returns - true, else - False;
	 * 
	 * @param productName
	 * @return
	 */
	public boolean isStringValid(String productName) {
		String regx = "^[a-zA-z]+([\\s][a-zA-Z]+)*$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(productName);
		return matcher.find();
	}

	/**
	 * This method is used to check whether the entered quantity type is positive or
	 * not If Positive - true, Negative - false
	 * 
	 * @param productQuantity
	 * @return
	 */
	public boolean isNumberValid(int number) {
		boolean valid = true;
		if (number < 0) {
			valid = false;
		}
		return valid;
	}
}
