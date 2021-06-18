package in.siva.vegapp.VegetableShoppingApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.siva.vegapp.dao.UserRepository;

@SpringBootTest
class VegetableShoppingAppApplicationTests {

	@Autowired
	UserRepository userRepo;
	@Test
	void contextLoads() {
		String findRoleOfUser = userRepo.findRoleOfUser("siva123", "Siva123@");
		System.out.println(findRoleOfUser);
	}

}
