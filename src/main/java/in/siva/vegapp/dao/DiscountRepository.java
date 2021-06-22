package in.siva.vegapp.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.model.Discount;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Integer>{
	
	@Query("SELECT * FROM discount_details WHERE user_id = :user_id AND status = 'AVAILABLE'")
	List<Discount> findAllByUserId(@Param("user_id") Integer userId);
}
