package in.syncuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.entity.GrantedAuthority;
import in.syncuser.service.GrantedAuthorityService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	private GrantedAuthorityService grantedAuthorityService;
	
	@GetMapping("/fetchById")
	@PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<?> fetchById(@RequestParam("id") Integer id) {
		GrantedAuthority role = grantedAuthorityService.findById(id);
		if (role != null)
			return new ResponseEntity<>(role, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/findByRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> findByRole(@RequestParam("role") String name) {
		GrantedAuthority role = grantedAuthorityService.findByRole(name);
		if (role != null)
			return new ResponseEntity<>(role, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/fetchAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchById() {
		List<GrantedAuthority> roles = grantedAuthorityService.fetchAll();
		if (roles != null && !roles.isEmpty())
			return new ResponseEntity<>(roles, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/fetchByUID")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchByUID(@RequestParam("UID") Long userId) {
		List<GrantedAuthority> roles = grantedAuthorityService.fetchByUID(userId);
		if (roles != null && !roles.isEmpty())
			return new ResponseEntity<>(roles, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
