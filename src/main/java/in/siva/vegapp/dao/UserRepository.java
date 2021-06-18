package in.siva.vegapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.model.UserDetail;

@Repository
public interface UserRepository extends CrudRepository<UserDetail, Integer>{

}
