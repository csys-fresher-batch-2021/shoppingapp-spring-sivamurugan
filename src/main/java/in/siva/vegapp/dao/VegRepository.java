package in.siva.vegapp.dao;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import in.siva.vegapp.model.Vegetable;

public interface VegRepository extends CrudRepository<Vegetable, Integer>{

	@Query("DELETE FROM veg_details WHERE name = :name RETURNING id")
	Integer deleteByName(@Param("name") String name);
	
	@Query("SELECT id FROM veg_details WHERE name = :name")
	Integer findIdByName(@Param("name") String name);
	
	@Query("UPDATE veg_details SET quantity = quantity - :purchased_quantity WHERE id = :id")
	@Modifying
	void updateStockById(@Param("purchased_quantity") Integer quantity, @Param("id") Integer vegId);
	
}
