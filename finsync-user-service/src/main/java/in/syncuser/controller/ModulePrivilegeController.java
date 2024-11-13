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
import in.syncuser.entity.ModulePrivilege;
import in.syncuser.service.ModulePrivilegeService;

@RestController
@RequestMapping("/modules/privileges")
public class ModulePrivilegeController {

	@Autowired
	private ModulePrivilegeService modulePrivilegeService;

	@PostMapping("/insert")
	@PreAuthorize("hasAnyAuthority('ADMIN','BANK')")
	public ResponseEntity<?> insertModulePrivilege(@RequestParam("roleTypeId") Integer roleTypeId,
			@RequestParam("systemModuleId") Integer systemModuleId) {
		try {
			ModulePrivilege modulePrivilege = modulePrivilegeService.insertModulePrivilege(roleTypeId, systemModuleId);
			if (modulePrivilege != null)
				return new ResponseEntity<ModulePrivilege>(modulePrivilege, HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(FinSyncConstants.DUPLICATE_ENTRY, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/fetchByRoleType")
	@PreAuthorize("hasAnyAuthority('ADMIN','BANK')")
	public ResponseEntity<?> fetchAllModulePrivilegesByRoleType(@RequestParam("roleTypeId") Integer roleTypeId) {
		List<ModulePrivilege> privileges = modulePrivilegeService.fetchAllModulePrivilegesByRoleTypeId(roleTypeId);
		if (privileges != null)
			return new ResponseEntity<List<ModulePrivilege>>(privileges, HttpStatus.OK);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/fetchBySystemModule")
	@PreAuthorize("hasAnyAuthority('ADMIN','BANK')")
	public ResponseEntity<?> fetchAllModulePrivilegesBySystemModule(
			@RequestParam("systemModuleId") Integer systemModuleId) {
		List<ModulePrivilege> privileges = modulePrivilegeService
				.fetchAllModulePrivilegesBySystemModule(systemModuleId);
		if (privileges != null)
			return new ResponseEntity<List<ModulePrivilege>>(privileges, HttpStatus.OK);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/fetchAll")
	@PreAuthorize("hasAnyAuthority('ADMIN','BANK')")
	public ResponseEntity<?> fetchAll() {
		List<ModulePrivilege> privileges = modulePrivilegeService.fetchAllModulePrivileges();
		if (privileges != null)
			return new ResponseEntity<List<ModulePrivilege>>(privileges, HttpStatus.OK);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
}
