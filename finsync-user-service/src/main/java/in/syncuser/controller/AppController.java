package in.syncuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.service.RoleService;

@RequestMapping("/app")
@RestController
public class AppController {
	
	@Autowired
	private RoleService roleService;
	
	public ResponseEntity<String> health(){
		roleService.fetchAll();
		return null;
	}

}
