package in.syncuser.service.impl;


import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import in.syncuser.config.JwtUtil;
import in.syncuser.constants.FynSyncConstants;
import in.syncuser.entity.User;
import in.syncuser.model.CommonModel;
import in.syncuser.model.EmailDetails;
import in.syncuser.model.LoginModel;
import in.syncuser.repository.UserRepository;
import in.syncuser.service.EmailSenderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import in.syncuser.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final EmailSenderService emailSenderService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	private Integer expirationTime = 60;
	
	public AuthServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService,
			BCryptPasswordEncoder passwordEncoder, AuthenticationManager authManager, JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.emailSenderService = emailSenderService;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public CommonModel authenticateUser(LoginModel login, HttpServletResponse response) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		CommonModel model = new CommonModel();
		if (authentication.isAuthenticated()) {
			String jwtToken = jwtUtil.generateAuthenticationToken(login.getUsername(), expirationTime);
			if (jwtToken != null) {
				User user = userRepository.findByUserName(login.getUsername());
				model.setId(user.getId());
				model.setFullName(user.getFullName());
				model.setEmail(user.getEmail());
				model.setPhoneNo(user.getPhoneNo());
				model.setFirstName(user.getFirstName());
				model.setLastName(user.getLastName());
				model.setMessage(FynSyncConstants.SUCCESS);
				model.setJwtToken(jwtToken);
				// EmailDetails mailParmas = emailSenderService.configureEmailParams(model,
				// FynSyncConstants.LOGIN_ALERT);
				// emailSenderService.sendEmailWithAttachment(mailParmas);
				configureTokenInCookie(jwtToken, response);
				return model;
			}
			model.setMessage(FynSyncConstants.JWT_NOT_AVAILABLE);
			return model;
		}
		model.setMessage(FynSyncConstants.AUTHENTICATION_FAILED);
		return model;
	}
	
	public void configureTokenInCookie(String jwtToken, HttpServletResponse response) {
		Cookie cookie = new Cookie("authToken", jwtToken);
        cookie.setHttpOnly(true); 
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(9 * 60 * 60);
        response.addCookie(cookie);
	}

	@Override
	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}
	
	@Override
	public Boolean secureAllPasswords() {
		List<User> users = userRepository.findAll();
		users.stream().forEach(user -> {
			user.setUserName("fsync" + user.getFirstName().substring(0, 1) + user.getId());
			user.setCode("fsync@" + user.getFirstName() + user.getId());
			user.setPassword(passwordEncoder("fsync@" + user.getFirstName() + user.getId())); // Hash the new password
		});
		userRepository.saveAll(users);
		return true;
	}

	@Override
	public Boolean sendResetPassword(String email) {
		User user = userRepository.findByEmail(email);
		CommonModel model = new CommonModel();
		model.setFullName(user.getFullName());
		model.setEmail(user.getEmail());
		EmailDetails mailParmas = emailSenderService.configureEmailParams(model, FynSyncConstants.SEND_RESET_SUBJECT);
		return emailSenderService.sendEmailWithAttachment(mailParmas);
	}

	@Override
	public Boolean resetPassword(String email) {
		User user = userRepository.findByEmail(email);
		CommonModel model = new CommonModel();
		model.setFullName(user.getFullName());
		model.setEmail(user.getEmail());
		EmailDetails mailParmas = emailSenderService.configureEmailParams(model, FynSyncConstants.SEND_RESET_SUBJECT);
		return emailSenderService.sendEmailWithAttachment(mailParmas);
	}

}
