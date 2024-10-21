package in.syncaccount.service;

import in.syncaccount.model.CommonModel;
import in.syncaccount.model.EmailDetails;

public interface EmailSenderService {
	
	public String sendTextEmail(EmailDetails emailDetails);

	EmailDetails configureEmailParams(CommonModel commonModel);

	String configureEmailMsgBody(CommonModel commonModel);

	String sendEmailWithAttachment(EmailDetails emailDetails);

	String configureMimeEmailMsgBody(CommonModel commonModel);


}
