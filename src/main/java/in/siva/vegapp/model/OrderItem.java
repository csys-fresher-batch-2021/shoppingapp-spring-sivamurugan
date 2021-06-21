package in.siva.vegapp.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderItem {
	private Long orderId;
	private Integer vegId;
	private Integer price;
	private Integer quantity;
	private Integer vegBill;
	private Boolean active;
}
