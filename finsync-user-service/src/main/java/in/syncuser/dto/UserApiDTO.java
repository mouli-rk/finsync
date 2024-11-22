package in.syncuser.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.syncuser.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserApiDTO {

	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private List<Role> roles;

	public UserApiDTO(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public UserApiDTO(Integer id, String username, String password, String firstName, String lastName, String email,
			String phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

}
