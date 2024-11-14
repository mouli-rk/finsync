package in.syncuser.service;

import java.util.List;

import in.syncuser.constants.Role;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.SystemModule;

public interface SystemModuleService {
	
	SystemModule insertSystemModule(String module);

	List<SystemModule> fetchAllSystemModules();

	List<RoleApiDTO> fetchModulesByRoleType(Role roleType);

}
