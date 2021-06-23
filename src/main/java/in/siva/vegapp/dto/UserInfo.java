package in.siva.vegapp.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserInfo {
	private String role;
	private String username;
	private String userId;
}
