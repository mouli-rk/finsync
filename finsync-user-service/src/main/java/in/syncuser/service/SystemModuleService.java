package in.syncuser.service;

import java.util.List;

import in.syncuser.constants.Role;
import in.syncuser.dto.ModuleDTO;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.SystemModule;

public interface SystemModuleService {
	
	SystemModule insertSystemModule(String module);

	List<SystemModule> fetchAllSystemModules();

	List<RoleApiDTO> fetchModulesByRoleType(Role roleType);

	List<RoleApiDTO> findModulesByUsernameAndRole(String username, Role role);

	SystemModule insertChildModule(String module, Integer parent);

	List<ModuleDTO> findChildModuleByUser(Integer user, Role role, Integer parent);

}
