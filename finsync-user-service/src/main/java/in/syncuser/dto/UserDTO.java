package in.syncuser.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.syncuser.constants.Role;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface UserDTO {
	Integer getId();
	String getCode();
	String getFirstName();
	String getLastName();
	String getFullName();
	String getUsername();
	String getPassword();
	String getEmail();
	String getPhoneNo();
	Boolean getStatus();
	List<Role> getRoles();
	void setRoles(List<Role> roles);
}
