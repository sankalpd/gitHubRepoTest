package com.ewt.dashboardpoint.service.user.impl.task;

import java.util.Arrays;

import com.ewt.dashboardpoint.framework.email.EmailUtil;
import com.ewt.dashboardpoint.framework.password.SecurityUtil;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.framework.logging.ILogger;


public class SendVerificationMailTask {
	private static String CLASS_NAME = SendVerificationMailTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method is used for sending a verification mail to the user.
	 * @return UserTable userTable
	 */
	public static boolean process(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "process(String pwd)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		//draft email contant :start
				String msgBody = "<p style=\"mso-line-height-alt:0;line-height:0;\"><h4><strong>Verification Mail</strong></h4>".concat("\n"); 
				msgBody =   msgBody+"<h4>Dear : "+userDetailBO.getFirstName().concat("</h4>\n");
				msgBody =	msgBody.concat("<h4>"+"Thank you for your interest in DashBoardPoint.</h4>\n");
				msgBody =	msgBody.concat("<h4>"+"Your Email Verification Code is : "+userDetailBO.getUserVerificationKey()+"</h4>\n");
				msgBody =	msgBody.concat("<h4><i>"+"Please login.</i>").concat("\n");
				//msgBody = msgBody.concat("<h4><u><i><b><a href='http://www.erudinova.com/'>Erudinova login</a><br>");
				//draft email contant :end
				
				//set email subject
				String emailSubject = "DashBoardPoint: Email Verification";
				
				//sending email 
				boolean isSuccessfull = EmailUtil.sendEmail(msgBody, Arrays.asList(userDetailBO.getUserEmailId().trim()), emailSubject);
				

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isSuccessfull;
	}
}

