package in.siva.vegapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table("order_items")
public class OrderItem {
	@Id
	private Long id;
	@Column("order_id")
	private Long orderId;
	@Column("veg_id")
	private Integer vegId;
	private Integer price;
	private Integer quantity;
	@Column("bill")
	private Integer vegBill;
}
