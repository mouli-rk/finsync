package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.syncuser.entity.ModulePrivilege;
import in.syncuser.entity.RoleType;
import in.syncuser.entity.SystemModule;
import in.syncuser.repository.ModulePrivilegeRepository;
import in.syncuser.service.ModulePrivilegeService;

@Service
public class ModulePrivilegeServiceImpl implements ModulePrivilegeService{
	
	@Autowired
	private ModulePrivilegeRepository modulePrivilegeRepository;
	
	@Override
	public ModulePrivilege insertModulePrivilege(Integer roleTypeId, Integer systemModuleId) {
		RoleType roleType = new RoleType(roleTypeId);
		SystemModule sytemModule = new SystemModule(systemModuleId);
		ModulePrivilege modulePrivilege= new ModulePrivilege(roleType, sytemModule);
		modulePrivilege = modulePrivilegeRepository.save(modulePrivilege);
		return modulePrivilege;
	}
	
	@Override
	public List<ModulePrivilege> fetchAllModulePrivilegesByRoleTypeId(Integer roleTypeId){
		List<ModulePrivilege> privileges= modulePrivilegeRepository.findAllModulePrivilegesByRoleTypeId(roleTypeId);
		return privileges;
	}
	
	@Override
	public List<ModulePrivilege> fetchAllModulePrivilegesBySystemModule(Integer systemModuleId){
		List<ModulePrivilege> privileges= modulePrivilegeRepository.findAllModulePrivilegesBySystemModuleId(systemModuleId);
		return privileges;
	}
	
	@Override
	public List<ModulePrivilege> fetchAllModulePrivileges(){
		List<ModulePrivilege> privileges = modulePrivilegeRepository.findAll();
		return privileges; 
	}

}
