package in.syncuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.constants.FinSyncConstants;
import in.syncuser.constants.Role;
import in.syncuser.dto.ModuleDTO;
import in.syncuser.dto.RoleApiDTO;
import in.syncuser.entity.SystemModule;
import in.syncuser.service.SystemModuleService;

@RestController
@RequestMapping("/system/module")
public class SystemModuleController {

	@Autowired
	private SystemModuleService systemModuleService;

	@PostMapping("/insert")
	@PreAuthorize("hasAnyAuthority('ADMIN','BANK')")
	public ResponseEntity<?> insert(@RequestParam("module") String module) {
		try {
			SystemModule systemModule = systemModuleService.insertSystemModule(module);
			if (systemModule != null)
				return new ResponseEntity<SystemModule>(systemModule, HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(FinSyncConstants.DUPLICATE_ENTRY, HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/insert/child")
	@PreAuthorize("hasAnyAuthority('ADMIN','BANK')")
	public ResponseEntity<?> insertChildModule(@RequestParam String module, @RequestParam Integer parent) {
		try {
			SystemModule systemModule = systemModuleService.insertChildModule(module, parent);
			if (systemModule != null)
				return new ResponseEntity<SystemModule>(systemModule, HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(FinSyncConstants.DUPLICATE_ENTRY, HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/fetchModulesByRoleType")
	public ResponseEntity<List<RoleApiDTO>> fetchModulesByRoleType(@RequestParam("role") String role) {
		try {
			Role roleType = Role.valueOf(role);
			List<RoleApiDTO> modules = systemModuleService.fetchModulesByRoleType(roleType);
			if (modules != null && !modules.isEmpty()) {
				return new ResponseEntity<List<RoleApiDTO>>(modules, HttpStatus.OK);
			}
			return new ResponseEntity<List<RoleApiDTO>>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<List<RoleApiDTO>>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("/findModulesByUsernameAndRole")
	public ResponseEntity<List<RoleApiDTO>> findModulesByUsernameAndRole(@RequestParam("username") String username, @RequestParam("role") String role) {
		try {
			Role roleType = Role.valueOf(role);
			List<RoleApiDTO> modules = systemModuleService.findModulesByUsernameAndRole(username, roleType);
			if (modules != null && !modules.isEmpty()) {
				return new ResponseEntity<List<RoleApiDTO>>(modules, HttpStatus.OK);
			}
			return new ResponseEntity<List<RoleApiDTO>>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<List<RoleApiDTO>>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("/findChildModuleByUser")
	public ResponseEntity<List<ModuleDTO>> findChildModuleByUser(@RequestParam Integer user, @RequestParam(required = false) String role,
			@RequestParam(required = false) Integer parent) {
		Role roleType = Role.valueOf(role);
		List<ModuleDTO> modules = systemModuleService.findChildModuleByUser(user, roleType, parent);
		if(modules != null && !modules.isEmpty()) {
			return new ResponseEntity<List<ModuleDTO>>(modules, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<SystemModule>> fetchAll() {
		List<SystemModule> modules = systemModuleService.fetchAllSystemModules();
		if (modules != null && !modules.isEmpty()) {
			return new ResponseEntity<List<SystemModule>>(modules, HttpStatus.OK);
		}
		return new ResponseEntity<List<SystemModule>>(HttpStatus.NO_CONTENT);
	} 

}
