package in.siva.vegapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(value = "veg_details")
public class Vegetable {
	
	@Id
	private Integer id;
	private String name;
	private Integer price;
	private Integer quantity;
}
