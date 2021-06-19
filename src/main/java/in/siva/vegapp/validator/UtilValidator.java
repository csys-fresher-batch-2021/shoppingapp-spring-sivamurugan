package in.siva.vegapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.exception.UserRepeatedException;
import in.siva.vegapp.model.UserDetail;

@Component
public class UtilValidator {

	@Autowired
	UserRepository userRepo;

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

	public boolean isMobileNoRepeated(Long mobileNo) {
		boolean exists = true;
		Long id = userRepo.findIdByMobile(mobileNo);
		if (id == null) {
			exists = false;
		}
		return exists;
	}
	
	public boolean isEmailRepeated(String email) {
		boolean exists = true;
		Long id = userRepo.findIdByEmail(email);
		if(id == null) {
			exists = false;
		}
		return exists;
	}
}
