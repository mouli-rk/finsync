package in.syncuser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.dto.UserApiDTO;
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
	public User fetchById(@RequestParam("id") Long id){
		return userService.fetchById(id);
	}
	
	@GetMapping("/fetchUserDetails")
	public ResponseEntity<UserApiDTO> fetchUserDetails(@RequestParam("username") String apiPayLoad){
		UserApiDTO apiResponse = userService.fetchUserDetails(apiPayLoad).orElse(null);
		if(apiResponse!=null) {
			return new ResponseEntity<UserApiDTO>(apiResponse, HttpStatus.OK);
		}
		return new ResponseEntity<UserApiDTO>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/fetchAll")
	public List<User> fetchAllUsers(){
		return userService.fetchAllUsers();
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
