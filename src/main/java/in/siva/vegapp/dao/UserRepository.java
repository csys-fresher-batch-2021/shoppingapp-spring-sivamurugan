package in.siva.vegapp.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.model.UserDetail;

@Repository
public interface UserRepository extends CrudRepository<UserDetail, Integer>{

	@Query("SELECT role FROM user_details WHERE username = :username AND password = :password")
	String findRoleOfUser(@Param("username") String username, @Param("password") String password);
	
	@Query("SELECT id FROM user_details WHERE username = :username")
	Long findUserId(@Param("username") String username);
	
	@Query("UPDATE user_details SET name = :name WHERE id = :id RETURNING id")
	Long updateNameById(@Param("name") String name, @Param("id") Long id);
	
	@Query("UPDATE user_details SET mobile_no = :mobile_no WHERE id = :id RETURNING id")
	Long updateMobileNoById(@Param("mobile_no") Long mobileNo, @Param("id") Long id);
	
	@Query("SELECT id FROM user_details WHERE mobile_no = :mobile_no")
	Long findIdByMobile(@Param("mobile_no") Long mobileNo);
	
	@Query("SELECT id FROM user_details WHERE email = :email")
	Long findIdByEmail(@Param("email") String email);
	
	@Query("UPDATE user_details SET email = :email WHERE id = :id RETURNING id")
	Long updateEmailById(@Param("email") String email, @Param("id") Long id);	
}
