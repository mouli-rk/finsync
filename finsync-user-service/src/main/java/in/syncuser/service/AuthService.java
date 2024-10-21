package in.syncuser.service;

import in.syncuser.model.CommonModel;
import in.syncuser.model.LoginModel;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	Boolean sendResetPassword(String email);

	Boolean resetPassword(String email);

	String passwordEncoder(String password);

	Boolean secureAllPasswords();

	CommonModel authenticateUser(LoginModel login, HttpServletResponse response);	

}
