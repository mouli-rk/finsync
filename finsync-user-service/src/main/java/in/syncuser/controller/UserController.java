package in.syncuser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.dto.UserApiDTO;
import in.syncuser.dto.UserDTO;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;
import in.syncuser.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/fetchById")
	public User fetchById(@RequestParam("id") Long id) {
		return userService.fetchById(id);
	}

	@GetMapping("/fetchUserDetails")
	public ResponseEntity<UserApiDTO> fetchUserDetails(@RequestParam(required = false) String username) {
		UserApiDTO apiResponse = userService.fetchUserDetails(username).orElse(null);
		if (apiResponse != null) {
			return new ResponseEntity<UserApiDTO>(apiResponse, HttpStatus.OK);
		}
		return new ResponseEntity<UserApiDTO>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/fetchAll")
	public List<User> fetchAllUsers() {
		return userService.fetchAllUsers();
	}

	@GetMapping("/findByCode")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserDTO>> findByCode(@RequestParam("code") String code) {
		List<UserDTO> users = userService.findByCode(code);
		if (users != null && !users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/findByAllName")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserDTO>> findByAllName(@RequestParam(required = false) String name) {
		List<UserDTO> users = userService.findByAllName(name);
		if (users != null && !users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/loadUserGrid")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserDTO>> loadUserGrid(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) Boolean status) {
		List<UserDTO> users = userService.loadUserGrid(id, status);
		if (users != null && !users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/createUser")
	public CommonModel createUser(@RequestBody CommonModel userModel) {
		return userService.createUser(userModel);
	}

	@GetMapping("/")
	public String getDefaultResponse() {
		return "You are seeing a default page..!";
	}

}
