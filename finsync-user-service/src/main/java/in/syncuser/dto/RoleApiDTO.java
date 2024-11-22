package in.syncuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.syncuser.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleApiDTO {
	private Integer id;
	private Role role;
	private Integer userId;
	private String module;
	
	public RoleApiDTO(Integer id, Role role) {
		super();
		this.id = id;
		this.role = role;
	}

}
