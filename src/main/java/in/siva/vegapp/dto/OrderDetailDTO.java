package in.siva.vegapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import in.siva.vegapp.model.OrderItem;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDetailDTO {
	private Long orderId;
	private Integer userId;
	private String status;
	private Boolean active;
	private List<OrderItem> orderItems;
	private LocalDateTime createdDate;
	private LocalDate deliveryDate;
	private String paymentMethod;
	private String address;
}
