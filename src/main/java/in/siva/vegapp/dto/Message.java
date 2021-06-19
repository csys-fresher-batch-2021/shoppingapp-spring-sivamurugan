package in.siva.vegapp.dto;

import lombok.Data;

@Data
public class Message {
	private String infoMessage;
	private String errorMessage;
	private UserInfo userInfo;
}
