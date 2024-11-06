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
	
	private Long id;
	private String username;
	private String password;
	private List<Role> roles;
	
	public UserApiDTO(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	

}
