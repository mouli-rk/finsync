package in.syncuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.syncuser.entity.ModulePrivilege;

public interface ModulePrivilegeRepository extends JpaRepository<ModulePrivilege, Integer>{
	
	public List<ModulePrivilege> findAllModulePrivilegesByRoleTypeId(Integer roleId);
	
	public List<ModulePrivilege> findAllModulePrivilegesBySystemModuleId(Integer moduleId);

}
