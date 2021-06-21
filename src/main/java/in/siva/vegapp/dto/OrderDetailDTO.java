package in.siva.vegapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import in.siva.vegapp.model.OrderItem;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDetailDTO {
	private Long orderId;
	private String username;
	private Double totalBill;
	private String status;
	private Boolean active;
	private List<OrderItem> orderItems;
	private LocalDateTime createdDate;
	private LocalDateTime deliveryDate;
	private String paymentMethod;
	private String address;
}
