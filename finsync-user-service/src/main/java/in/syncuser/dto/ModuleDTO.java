package in.syncuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public interface ModuleDTO {
	String getModule();
	Integer getId();
	Integer getParent();
	Integer getRole();
}
