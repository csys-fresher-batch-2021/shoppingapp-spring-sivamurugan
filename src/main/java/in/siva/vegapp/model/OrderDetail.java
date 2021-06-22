package in.siva.vegapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table("order_details")
public class OrderDetail {
	@Id
	@Column("order_id")
	private Long orderId;
	
	@Column("user_id")
	private Integer userId;
	
	private String status;
	private Boolean active;
	@Column("created_date")
	private LocalDateTime createdDate = LocalDateTime.now();
	
	@Column("delivery_date")
	@FutureOrPresent(message = "Invalid Delivery Date")
	private LocalDate deliveryDate;
	
	@Column("payment_method")
	private String paymentMethod;
	
	@Size(min = 2, max = 200, message = "Please enter valid address")
	private String address;
}
