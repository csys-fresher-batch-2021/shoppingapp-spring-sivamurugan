package in.siva.vegapp.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
	private String infoMessage;
	private String errorMessage;
	private UserInfo userInfo;
	private List<String> errors;
}
