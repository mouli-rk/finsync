package in.syncuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.syncuser.constants.Role;
import in.syncuser.entity.SystemModule;
import in.syncuser.repository.SystemModuleRepository;
import in.syncuser.service.SystemModuleService;

@Service
public class SystemModuleServiceImpl implements SystemModuleService{
	
	@Autowired
	private SystemModuleRepository systemModuleRepository;
	
	@Override
	public SystemModule insertSystemModule(String module) {
		SystemModule sytemModule = new SystemModule(module);
		sytemModule = systemModuleRepository.save(sytemModule);
		return sytemModule;
	}
	
	@Override
	public List<SystemModule> fetchModulesByRoleType(Role roleType){
		List<SystemModule> modules = systemModuleRepository.findModulesByRoleType(roleType);
		return modules;
	}
	
	@Override
	public List<SystemModule> fetchAllSystemModules() {
		List<SystemModule> modules = systemModuleRepository.findAll();
		return modules;
	}
	
}
