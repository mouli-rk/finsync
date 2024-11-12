package in.syncuser.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginModel {
	private String username;
	private String password;
	private String jwtToken;
	private String message;
	private String email;
	private String phoneNo;
	private String role;
}
