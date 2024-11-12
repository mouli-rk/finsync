package in.syncuser.service;

import java.util.List;

import in.syncuser.entity.ModulePrivilege;

public interface ModulePrivilegeService {

	List<ModulePrivilege> fetchAllModulePrivilegesBySystemModule(Integer systemModuleId);

	List<ModulePrivilege> fetchAllModulePrivilegesByRoleTypeId(Integer roleTypeId);

	ModulePrivilege insertModulePrivilege(Integer roleTypeId, Integer systemModuleId);

	List<ModulePrivilege> fetchAllModulePrivileges();

}
