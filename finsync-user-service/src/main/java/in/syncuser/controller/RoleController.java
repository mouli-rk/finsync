package in.syncuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.entity.GrantedAuthority;
import in.syncuser.model.RoleModel;
import in.syncuser.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/insert")
	@PreAuthorize("hasAuthority('ADMIN','BANK')")
	public ResponseEntity<?> insertRole(@RequestBody RoleModel apiPayload) {
		GrantedAuthority role = roleService.insertRole(apiPayload);
		if (role != null)
			return new ResponseEntity<>(role, HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/fetchById")
	@PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<?> fetchById(@RequestParam("id") Integer id) {
		GrantedAuthority role = roleService.findById(id);
		if (role != null)
			return new ResponseEntity<>(role, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/findByRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> findByRole(@RequestParam("role") String name) {
		GrantedAuthority role = roleService.findByRole(name);
		if (role != null)
			return new ResponseEntity<>(role, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/fetchAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchById() {
		List<GrantedAuthority> roles = roleService.fetchAll();
		if (roles != null && !roles.isEmpty())
			return new ResponseEntity<>(roles, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/fetchByUID")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchByUID(@RequestParam("UID") Long userId) {
		List<GrantedAuthority> roles = roleService.fetchByUID(userId);
		if (roles != null && !roles.isEmpty())
			return new ResponseEntity<>(roles, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}