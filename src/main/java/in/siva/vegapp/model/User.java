package in.siva.vegapp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(value = "user_details")
public class User {
	@Id
	private Integer id;

	@NotBlank(message = "Name should not be empty")
	@Size(min = 3, max = 25, message = "Please Enter your name correctly")
	private String name;

	@Min(value = 16, message = "Sorry! 16 years and above required to join with us")
	@Max(value = 100, message = "Please Enter valid age")
	private Integer age;

	@Size(min = 1, max = 1, message = "Gender should contain a single character")
	private String gender; // 'M' - Male, 'F'-Female, 'O'-Others/Rather not to say

	@Column(value = "mobile_no")
	private Long mobileNumber;

	@NotBlank(message = "Email should not be empty")
	@Email(message = "Invalid email ID")
	private String email;

	@NotBlank(message = "username should not be empty")
	@Size(min = 6, max = 20, message = "Username should contain 6 to 20 characters only")
	private String username;

	@NotBlank(message = "Password should not be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$", message = "Password must contain a number, uppercase, lowercase and a special character")
	private String password;

	@Size(min = 1, max = 1, message = "Role should contain a single character")
	private String role = "C"; // 'C' - Customer, 'S' - SalesMan, 'A' - Admin
}
