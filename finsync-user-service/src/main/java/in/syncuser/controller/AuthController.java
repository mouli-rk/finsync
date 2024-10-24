package in.syncuser.controller;

//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.syncuser.config.JwtUtil;
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
	public CommonModel authenticateUser(@RequestBody LoginModel login, HttpServletResponse response) {
		return authService.authenticateUser(login, response);	
	}

	@PostMapping("/send/resetLink")
	public Boolean sendResetPassword(@RequestParam("email") String email) {
		return authService.sendResetPassword(email);
	}

	@GetMapping("/reset")
	public Boolean resetPassword(String email) {
		return authService.sendResetPassword(email);
	}
	@GetMapping("/secureAllPasswords")
	public Boolean secureAllPasswords() {
		return authService.secureAllPasswords();
	}
	
	@GetMapping("/getJWTGeneratedToken")
	public String getJWTGeneratedToken() {
		return JwtUtil.getJwtToken();
	}
}
