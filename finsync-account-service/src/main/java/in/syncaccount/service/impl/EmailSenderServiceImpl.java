package in.syncaccount.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import in.syncaccount.constants.FynSyncConstants;
import in.syncaccount.model.CommonModel;
import in.syncaccount.model.EmailDetails;
import in.syncaccount.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public EmailDetails configureEmailParams(CommonModel commonModel) {
		EmailDetails mailParams = new EmailDetails();
		mailParams.setSubject(FynSyncConstants.ACCOUNT_CREATE_SUBJECT);
		mailParams.setMsgBody(configureMimeEmailMsgBody(commonModel));
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
	public String sendEmailWithAttachment(EmailDetails emailDetails) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mailParams;
		try {
			mailParams = new MimeMessageHelper(mimeMessage, true);
			mailParams.setFrom(sender);
			mailParams.setSubject(emailDetails.getSubject());
			mailParams.setText(emailDetails.getMsgBody(), true);
			mailParams.setTo(emailDetails.getRecipient());
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Mail sent sucessfully";
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
	public String configureMimeEmailMsgBody(CommonModel commonModel) {
		String msgBody = "<p>Dear " + commonModel.getFullName() + ",</p>"
		        + "<p>We are delighted to inform you that your account with Finsync has been successfully created. We look forward to supporting you in achieving your financial goals. Below, please find your account details:</p>"
		        + "<h3>Account Information</h3><br>"
		        + "<p><strong>Account Holder:</strong> " + commonModel.getFullName() + "<br>"
		        + "<strong>Username:</strong> " + commonModel.getFullName().toLowerCase() + "<br>"
		        + "<strong>Email:</strong> " + commonModel.getEmail() + "<br>"
		        + "<strong>Phone Number:</strong> " + commonModel.getPhoneNo() + "<br>"
		        + "<strong>Account Number:</strong> " + commonModel.getAccountNumber() + "<br>"
		        + "<strong>Account Type:</strong> " + commonModel.getAccountType() + "<br>"
		        + "<strong>Account Balance:</strong> " + commonModel.getAccountBalance() + "<br>"
		        + "<strong>Status:</strong> " + (commonModel.getStatus() ? "ACTIVE" : "INACTIVE") + "</p>"
		        + "<h3>Address Details</h3>"
		        + "<p><strong>DR No:</strong> " + commonModel.getDrNo() + "<br>"
		        + "<strong>Street:</strong> " + commonModel.getStreet() + "<br>"
		        + "<strong>City:</strong> " + commonModel.getCityName() + "<br>"
		        + "<strong>District:</strong> " + commonModel.getDistrictName() + "<br>"
		        + "<strong>State:</strong> " + commonModel.getStateName() + "<br>"
		        + "<strong>Country:</strong> " + commonModel.getCountryName() + "<br>"
		        + "<strong>Pin Code:</strong> " + commonModel.getPincode() + "</p><br>"
		        + "<h3>Next Steps</h3>"
		        + "<p>As a valued customer, we invite you to explore our wide range of services, including:</p>"
		        + "<ul>"
		        + "<li>Online Banking: Access your account anytime, anywhere.</li>"
		        + "<li>Mobile App: Manage your finances on the go with our user-friendly app.</li>"
		        + "<li>Customer Support: Our dedicated team is here to assist you 24/7.</li>"
		        + "</ul>"
		        + "<h3>Important Information</h3>"
		        + "<p><strong>Security Tips:</strong> Always keep your account information confidential and report any suspicious activity immediately.<br>"
		        + "<strong>Account Statement:</strong> You will receive monthly statements via email to help you keep track of your transactions.</p>"
		        + "<p>We appreciate your choice of Finsync and are committed to providing you with exceptional service. Should you have any questions or require further assistance, please feel free to contact our customer support team at 1800 425 425 or visit our website at www.finsync.com.</p>"
		        + "<p>Thank you for banking with us! We are excited to have you on board and look forward to serving your banking needs.</p>"
		        + "<p>Best Regards,<br>Your Manager,<br>Finsync Bank</p>";
		return msgBody;
	}

}
