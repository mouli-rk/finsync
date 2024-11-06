package in.syncuser.service;

import in.syncuser.model.CommonModel;
import in.syncuser.model.LoginModel;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	String sendResetPassword(LoginModel login);

	String resetPassword(LoginModel login);

	String passwordEncoder(String password);

	Boolean secureAllPasswords();

	CommonModel authenticate(LoginModel login, HttpServletResponse response);	

}
