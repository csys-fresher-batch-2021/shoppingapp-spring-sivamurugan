package in.siva.vegapp.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
@Table("discount_details")
public class Discount {
	@Id
	@Column("id")
	private Integer discountId;
	
	@Column("user_id")
	private Integer userId;
	
	private String coupon;
	private int amount;
	private String status;
	
	@Column("expiry_date")
	private LocalDate expiryDate;
}
