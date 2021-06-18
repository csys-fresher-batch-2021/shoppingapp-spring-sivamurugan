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
	
}
