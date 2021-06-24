package in.siva.vegapp.dao;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.dto.UserInfo;
import in.siva.vegapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT role, id FROM user_details WHERE username = :username AND password = :password")
	UserInfo findUserInfo(@Param("username") String username, @Param("password") String password);

	@Query("SELECT id FROM user_details WHERE username = :username")
	Long findUserId(@Param("username") String username);

	@Query("UPDATE user_details SET name = :name WHERE id = :id")
	@Modifying
	Integer updateNameById(@Param("name") String name, @Param("id") Long id);

	@Query("UPDATE user_details SET mobile_no = :mobile_no WHERE id = :id")
	@Modifying
	Integer updateMobileNoById(@Param("mobile_no") Long mobileNo, @Param("id") Long id);

	@Query("SELECT id FROM user_details WHERE mobile_no = :mobile_no")
	Long findIdByMobile(@Param("mobile_no") Long mobileNo);

	@Query("SELECT id FROM user_details WHERE email = :email")
	Long findIdByEmail(@Param("email") String email);

	@Query("UPDATE user_details SET email = :email WHERE id = :id")
	@Modifying
	Integer updateEmailById(@Param("email") String email, @Param("id") Long id);
}
