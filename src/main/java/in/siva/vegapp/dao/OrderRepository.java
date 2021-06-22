package in.siva.vegapp.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.vegapp.model.OrderDetail;

@Repository
public interface OrderRepository extends CrudRepository<OrderDetail, Long>{

	@Query("SELECT * FROM order_details WHERE user_id = :user_id")
	List<OrderDetail> findByUserId(@Param("user_id") Integer userId);
	
	@Query("SELECT * FROM order_details WHERE status IN ('PENDING', 'HOLD') and delivery_date = :delivery_date")
	List<OrderDetail> findForDelivery(@Param("delivery_date") LocalDate deliveryDate);
}
