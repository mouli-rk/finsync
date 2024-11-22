package in.syncuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface UserDTO {
	Integer getId();
	String getCode();
	String getFirstName();
	String getLastName();
	String getFullName();
	Boolean getStatus();
	String getPhoneNo();
}
