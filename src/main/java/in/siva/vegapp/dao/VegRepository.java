package in.siva.vegapp.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import in.siva.vegapp.model.VegDetail;

public interface VegRepository extends CrudRepository<VegDetail, Integer>{

	@Query("DELETE FROM veg_details WHERE name = :name RETURNING id")
	Integer deleteByName(@Param("name") String name);
	
	@Query("SELECT id FROM veg_details WHERE name = :name")
	Integer findIdByName(@Param("name") String name);
	
}
