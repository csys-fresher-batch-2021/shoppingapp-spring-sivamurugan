package in.siva.vegapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.UserRepository;
import in.siva.vegapp.model.UserDetail;
import in.siva.vegapp.validator.UtilValidator;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UtilValidator utilValidator;
	
	public void registerUser(UserDetail user) {
		if(utilValidator.isUserNotRepeated(user)) {
			userRepo.save(user);
		}
	}
}
