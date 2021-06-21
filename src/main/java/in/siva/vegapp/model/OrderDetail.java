package in.siva.vegapp.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDetail {
	private Long orderId;
	private String username;
	private Double totalBill;
	private String status;
	private Boolean active;
	private LocalDateTime createdDate;
	private LocalDateTime deliveryDate;
	private String paymentMethod;
	private String address;
}
