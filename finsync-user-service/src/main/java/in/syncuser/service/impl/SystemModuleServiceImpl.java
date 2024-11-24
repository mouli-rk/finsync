package in.syncuser.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.syncuser.constants.Role;
import in.syncuser.dto.ModuleDTO;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.SystemModule;
import in.syncuser.repository.SystemModuleRepository;
import in.syncuser.service.SystemModuleService;

@Service
public class SystemModuleServiceImpl implements SystemModuleService {

	@Autowired
	private SystemModuleRepository systemModuleRepository;

	@Override
	public SystemModule insertSystemModule(String module) {
		SystemModule sytemModule = new SystemModule(module);
		sytemModule = systemModuleRepository.save(sytemModule);
		return sytemModule;
	}
	
	@Override
	public SystemModule insertChildModule(String module, Integer parent) {
		SystemModule sytemModule = new SystemModule(module, new SystemModule(parent));
		sytemModule = systemModuleRepository.save(sytemModule);
		return sytemModule;
	}

	@Override
	public List<RoleApiDTO> fetchModulesByRoleType(Role roleType) {
		List<String> modules = systemModuleRepository.findModulesByRoleType(roleType);
		List<RoleApiDTO> systemModules = modules.stream().map(module -> {
			RoleApiDTO dto = new RoleApiDTO();
			dto.setModule(module);
			return dto;
		}).collect(Collectors.toList());
		return systemModules;
	}
	
	@Override
	public List<RoleApiDTO> findModulesByUsernameAndRole(String username, Role role) {
		List<String> modules = systemModuleRepository.findModulesByUsernameAndRole(username, role);
		List<RoleApiDTO> systemModules = modules.stream().map(module -> {
			RoleApiDTO dto = new RoleApiDTO();
			dto.setModule(module);
			return dto;
		}).collect(Collectors.toList());
		return systemModules;
	}
	
	@Override
	public List<ModuleDTO> findChildModuleByUser(Integer user, Role role, Integer parent){
		List<ModuleDTO> modules = systemModuleRepository.findChildModuleByUser(user, role, parent);
		return modules;
	}

	@Override
	public List<SystemModule> fetchAllSystemModules() {
		List<SystemModule> modules = systemModuleRepository.findAll();
		return modules;
	}

}
