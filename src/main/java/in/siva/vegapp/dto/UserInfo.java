package in.siva.vegapp.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table("user_details")
public class UserInfo {
	private String role;

	@Id
	@Column("id")
	private String userId;
}
