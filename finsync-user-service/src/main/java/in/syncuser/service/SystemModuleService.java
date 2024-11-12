package in.syncuser.service;

import java.util.List;

import in.syncuser.entity.SystemModule;

public interface SystemModuleService {
	
	SystemModule insertSystemModule(String module);

	List<SystemModule> fetchAllSystemModules();

}
