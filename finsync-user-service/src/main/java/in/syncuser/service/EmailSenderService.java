package in.syncuser.service;

import in.syncuser.model.CommonModel;
import in.syncuser.model.EmailDetails;

public interface EmailSenderService {

	public String sendTextEmail(EmailDetails emailDetails);

	String configureEmailMsgBody(CommonModel commonModel);

	Boolean sendEmailWithAttachment(in.syncuser.model.EmailDetails emailDetails);

	String configureResetPasswordMsgBody(CommonModel commonModel);

	String configureLoginAlertMsgBody(CommonModel commonModel);

	EmailDetails configureEmailParams(CommonModel commonModel, String subject);

}
