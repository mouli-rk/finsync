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

	@GetMapping("/fetchAll")
	public ResponseEntity<List<SystemModule>> fetchAll() {
		List<SystemModule> modules = systemModuleService.fetchAllSystemModules();
		if (modules != null) {
			return new ResponseEntity<List<SystemModule>>(modules, HttpStatus.OK);
		}
		return new ResponseEntity<List<SystemModule>>(HttpStatus.NO_CONTENT);
	} 

}