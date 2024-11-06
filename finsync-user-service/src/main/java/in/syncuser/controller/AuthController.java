package in.syncuser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.config.JwtUtils;
import in.syncuser.model.CommonModel;
import in.syncuser.model.LoginModel;
import in.syncuser.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
/*@CrossOrigin(origins = { "http://localhost:3000" })*/
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<CommonModel> authenticate(@RequestBody LoginModel apiRequest, HttpServletResponse response) {
		CommonModel apiResponse = authService.authenticate(apiRequest, response);
		if(apiResponse!=null) {
			return new ResponseEntity<CommonModel>(apiResponse, HttpStatus.OK);
		}
		return new ResponseEntity<CommonModel>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/resetLink")
	public ResponseEntity<String> sendResetPassword(@RequestBody LoginModel login) {
		String apiResponse = authService.sendResetPassword(login);
		if(apiResponse!=null && !apiResponse.isBlank()) {
			return new ResponseEntity<String>(apiResponse, HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/reset")
	public String resetPassword(@RequestBody LoginModel login) {
		return authService.resetPassword(login);
	}
	@GetMapping("/secureAllPasswords")
	public Boolean secureAllPasswords() {
		return authService.secureAllPasswords();
	}
	
	@GetMapping("/getJWTGeneratedToken")
	public String getJWTGeneratedToken() {
		return JwtUtils.getJwtToken();
	}
}
