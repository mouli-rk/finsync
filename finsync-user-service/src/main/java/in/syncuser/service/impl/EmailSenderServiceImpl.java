package in.syncuser.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import in.syncuser.config.JwtUtil;
import in.syncuser.constants.FinSyncConstants;
import in.syncuser.model.CommonModel;
import in.syncuser.model.EmailDetails;
import in.syncuser.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender javaMailSender;
	
	private final JwtUtil jwtUtil;
	
	public EmailSenderServiceImpl(JavaMailSender javaMailSender, JwtUtil jwtUtil) {
		super();
		this.javaMailSender = javaMailSender;
		this.jwtUtil = jwtUtil;
	}

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public EmailDetails configureEmailParams(CommonModel commonModel, String subject) {
		EmailDetails mailParams = new EmailDetails();
		switch (subject) {
			case FinSyncConstants.SEND_RESET_SUBJECT: {
				mailParams.setSubject(FinSyncConstants.SEND_RESET_SUBJECT);
				mailParams.setMsgBody(configureResetPasswordMsgBody(commonModel));
			}
			case FinSyncConstants.LOGIN_ALERT: {
				mailParams.setSubject(FinSyncConstants.LOGIN_ALERT);
				mailParams.setMsgBody(configureLoginAlertMsgBody(commonModel));
			}
		}
		mailParams.setRecipient(commonModel.getEmail());
		return mailParams;
	}

	@Override
	public String sendTextEmail(EmailDetails emailDetails) {
		SimpleMailMessage mailParams = new SimpleMailMessage();
		mailParams.setFrom(sender);
		mailParams.setSubject(emailDetails.getSubject());
		mailParams.setText(emailDetails.getMsgBody());
		mailParams.setTo(emailDetails.getRecipient());
		//javaMailSender.send(mailParams);
		return "Mail sent sucessfully";
	}
	
	@Override
	public Boolean sendEmailWithAttachment(EmailDetails emailDetails) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mailParams;
		try {
			mailParams = new MimeMessageHelper(mimeMessage, true);
			mailParams.setFrom(sender);
			mailParams.setSubject(emailDetails.getSubject());
			mailParams.setText(emailDetails.getMsgBody(), true);
			mailParams.setTo(emailDetails.getRecipient());
			//javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public String configureEmailMsgBody(CommonModel commonModel) {
		String msgBody = "Dear "+commonModel.getFullName()+",\r\n"
				+ "\r\n"
				+ "We are delighted to inform you that your account with Finsync has been successfully created. We look forward to supporting you in achieving your financial goals. Below, please find your account details:\r\n"
				+ "\r\n"
				+ "Account Information\r\n"
				+ "Account Holder: "+commonModel.getFullName()+"\r\n"
				+ "Username: "+commonModel.getFullName().toLowerCase()+"\r\n"
				+ "Email: "+commonModel.getEmail()+"\r\n"
				+ "Phone Number: "+commonModel.getPhoneNo()+"\r\n"
				+ "Account Number: "+commonModel.getAccountNumber()+"\r\n"
				+ "Account Type: "+commonModel.getAccountType()+"\r\n"
				+ "Account Balance: "+commonModel.getAccountBalance()+"\r\n"
				+ "Status: "+(commonModel.getStatus()?"ACTIVE":"IN ACTIVE")+"\r\n"
				+ "Address Details\r\n"
				+ "DR No: "+commonModel.getDrNo()+"\r\n"
				+ "Street: "+commonModel.getStreet()+"\r\n"
				+ "City: "+commonModel.getCityName()+"\r\n"
				+ "District: "+commonModel.getDistrictName()+"\r\n"
				+ "State: "+commonModel.getStateName()+"\r\n"
				+ "Country: "+commonModel.getCountryName()+"\r\n"
				+ "Pin Code: "+commonModel.getPincode()+"\r\n"
				+ "Next Steps\r\n"
				+ "As a valued customer, we invite you to explore our wide range of services, including:\r\n"
				+ "\r\n"
				+ "Online Banking: Access your account anytime, anywhere.\r\n"
				+ "Mobile App: Manage your finances on the go with our user-friendly app.\r\n"
				+ "Customer Support: Our dedicated team is here to assist you 24/7.\r\n"
				+ "Important Information\r\n"
				+ "Security Tips: Always keep your account information confidential and report any suspicious activity immediately.\r\n"
				+ "Account Statement: You will receive monthly statements via email to help you keep track of your transactions.\r\n"
				+ "We appreciate your choice of [Your Bank Name] and are committed to providing you with exceptional service. Should you have any questions or require further assistance, please feel free to contact our customer support team at 1800 425 425 or visit our website at www.finsync.com.\r\n"
				+ "\r\n"
				+ "Thank you for banking with us! We are excited to have you on board and look forward to serving your banking needs.\r\n"
				+ "\r\n"
				+ "Best Regards,\r\n"
				+ "Your Manager,\r\n"
				+ "Finsync Bank";
		return msgBody;
	}
	
	
	@Override
	public String configureResetPasswordMsgBody(CommonModel model) {
	    String jwtToken = jwtUtil.generateAuthenticationToken(model.getUsername(), 10);
	    String redirectUrl = "http://localhost:3000/api/reset/" + jwtToken;        
	    
	    String msgBody = "<!DOCTYPE html>\r\n"
	            + "<html lang=\"en\">\r\n"
	            + "<head>\r\n"
	            + "    <meta charset=\"UTF-8\">\r\n"
	            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
	            + "    <title>Password Reset Request</title>\r\n"
	            + "    <style>\r\n"
	            + "        body {\r\n"
	            + "            font-family: Arial, sans-serif;\r\n"
	            + "            background-color: #f4f4f4;\r\n"
	            + "            margin: 0;\r\n"
	            + "            padding: 20px;\r\n"
	            + "        }\r\n"
	            + "        .container {\r\n"
	            + "            background-color: #ffffff;\r\n"
	            + "            padding: 20px;\r\n"
	            + "            border-radius: 5px;\r\n"
	            + "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\r\n"
	            + "        }\r\n"
	            + "        .header {\r\n"
	            + "            text-align: center;\r\n"
	            + "            padding-bottom: 10px;\r\n"
	            + "        }\r\n"
	            + "        .button {\r\n"
	            + "            background-color: #007BFF;\r\n"
	            + "            color: white;\r\n"
	            + "            padding: 10px 15px;\r\n"
	            + "            text-decoration: none;\r\n"
	            + "            border-radius: 5px;\r\n"
	            + "        }\r\n"
	            + "        .footer {\r\n"
	            + "            text-align: center;\r\n"
	            + "            margin-top: 20px;\r\n"
	            + "            font-size: 12px;\r\n"
	            + "            color: #888888;\r\n"
	            + "        }\r\n"
	            + "    </style>\r\n"
	            + "</head>\r\n"
	            + "<body>\r\n"
	            + "    <div class=\"container\">\r\n"
	            + "        <div class=\"header\">\r\n"
	            + "            <h2>Password Reset Request</h2>\r\n"
	            + "        </div>\r\n"
	            + "        <p>Dear " + model.getFullName() + ",</p>\r\n"
	            + "        <p>We hope this message finds you well.</p>\r\n"
	            + "        <p>We are writing to inform you that we have received a request to reset the password for your Finsync account. If you did not initiate this request, please ignore this email. Your account remains secure.</p>\r\n"
	            + "        <p>To reset your password, please click the button below:</p>\r\n\n"
	            + "        <p><a href=\"" + redirectUrl + "\" class=\"button\">Reset Password</a></p>\r\n\n"
	            + "        <p>This link will expire in 10 minutes. If the link has expired, you may request a new password reset by visiting our website.</p>\r\n"
	            + "        <p>For any assistance, feel free to contact our customer support team at <a href=\"http://www.finsync.com\">www.finsync.com</a> or call 1800 425 425.</p>\r\n"
	            + "        <p>Thank you for choosing Finsync.</p>\r\n"
	            + "        <div class=\"footer\">\r\n"
	            + "            <p>Best regards,<br>Chandramouli P<br>Manager<br>Finsync Team</p>\r\n"
	            + "        </div>\r\n"
	            + "    </div>\r\n"
	            + "</body>\r\n"
	            + "</html>\r\n";

	    return msgBody;
	}
	
	@Override
	public String configureLoginAlertMsgBody(CommonModel commonModel) {
	    String msgBody = "<!DOCTYPE html>\r\n"
	            + "<html lang=\"en\">\r\n"
	            + "<head>\r\n"
	            + "    <meta charset=\"UTF-8\">\r\n"
	            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
	            + "    <title>Login Alert</title>\r\n"
	            + "    <style>\r\n"
	            + "        body {\r\n"
	            + "            font-family: Arial, sans-serif;\r\n"
	            + "            background-color: #f4f4f4;\r\n"
	            + "            margin: 0;\r\n"
	            + "            padding: 20px;\r\n"
	            + "        }\r\n"
	            + "        .container {\r\n"
	            + "            background-color: #ffffff;\r\n"
	            + "            padding: 20px;\r\n"
	            + "            border-radius: 5px;\r\n"
	            + "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\r\n"
	            + "        }\r\n"
	            + "        .header {\r\n"
	            + "            text-align: center;\r\n"
	            + "            padding-bottom: 10px;\r\n"
	            + "        }\r\n"
	            + "        .footer {\r\n"
	            + "            text-align: center;\r\n"
	            + "            margin-top: 20px;\r\n"
	            + "            font-size: 12px;\r\n"
	            + "            color: #888888;\r\n"
	            + "        }\r\n"
	            + "    </style>\r\n"
	            + "</head>\r\n"
	            + "<body>\r\n"
	            + "    <div class=\"container\">\r\n"
	            + "        <div class=\"header\">\r\n"
	            + "            <h2>Login Alert</h2>\r\n"
	            + "        </div>\r\n"
	            + "        <p>Dear " + commonModel.getFullName() + ",</p>\r\n"
	            + "        <p>We wanted to let you know that your account was just accessed.</p>\r\n"
	            + "        <p><strong>Login Details:</strong></p>\r\n"
	            + "        <ul>\r\n"
	            + "            <li><strong>Date:</strong> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "</li>\r\n"
	            + "        </ul>\r\n"
	            + "        <p>If this was you, no further action is needed. If you did not log in, please change your password immediately and contact our support team.</p>\r\n"
	            + "        <p>Thank you for keeping your account secure!</p>\r\n"
	            + "        <div class=\"footer\">\r\n"
	            + "            <p>Best regards,<br>Your Company Name<br>Support Team</p>\r\n"
	            + "        </div>\r\n"
	            + "    </div>\r\n"
	            + "</body>\r\n"
	            + "</html>\r\n";

	    return msgBody;
	}


}
