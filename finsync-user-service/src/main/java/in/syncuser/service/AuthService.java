package in.syncuser.service;

import in.syncuser.model.CommonModel;
import in.syncuser.model.LoginModel;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	Boolean sendResetPassword(LoginModel login);

	Boolean resetPassword(LoginModel login);

	String passwordEncoder(String password);

	Boolean secureAllPasswords();

	CommonModel authenticateUser(LoginModel login, HttpServletResponse response);	

}
