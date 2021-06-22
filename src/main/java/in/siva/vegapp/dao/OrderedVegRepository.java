package in.siva.vegapp.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.model.OrderItem;

@Repository
public interface OrderedVegRepository extends CrudRepository<OrderItem, Integer>{

	@Query("SELECT * FROM order_items WHERE order_id = :order_id")
	List<OrderItem> findAllByOrderId(@Param("order_id") Long orderId);
}
