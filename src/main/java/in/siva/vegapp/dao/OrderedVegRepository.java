package in.siva.vegapp.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.model.OrderedVeg;

@Repository
public interface OrderedVegRepository extends CrudRepository<OrderedVeg, Integer>{

	@Query("SELECT * FROM order_items WHERE order_id = :order_id")
	List<OrderedVeg> findAllByOrderId(@Param("order_id") Long orderId);
}
