package com.ewt.dashboardpoint.framework.email;


import java.util.HashSet;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.framework.logging.ILogger;

public class EmailUtil {
	private static String CLASS_NAME = EmailUtil.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * To send email.
	 * 
	 * @param mailTxt
	 * @param toAddress
	 * @param subject
	 */
	public static void sendEmail(String mailTxt, String toAddress,
			String subject) {
		final String METHOD_NAME = "sendEmail()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.debug("EMAIL SENDING IS COMMENTED");

		try {
			String host = "smtp.gmail.com";
			String from = ApplicationConstants.APP_EMAIL_ID;
			String pass = ApplicationConstants.APP_EMAIL_PWD;

			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));
			message.setSubject(subject);
			message.setText(mailTxt);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * To validate an email address.
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static boolean isValidEmailAddress(String emailAddress) {
		try {
			InternetAddress emailAddr = new InternetAddress(emailAddress);
			emailAddr.validate();
		} catch (AddressException ex) {
			logger.debug("inValid email address: " + emailAddress);
			ex.printStackTrace();

			return false;
		}
		logger.debug("Valid email address: " + emailAddress);
		return true;
	}

	public static boolean sendEmail(String msgBody, List<String> userList,
			String subject) {
		
		for (String temp : userList) {
			logger.debug("tarang users " + temp);
		}

		// Recipient's email ID needs to be mentioned.

		// Sender's email ID needs to be mentioned
		Set<String> uniqueEmailIdSet = new HashSet<String>();
		if (userList != null && userList.size() > 0) {
			uniqueEmailIdSet = new HashSet<String>(userList);
		}
		String from = "erudiconversation@gmail.com";// change accordingly
		final String username = "erudiconversation@gmail.com";// change
																// accordingly
		final String password = "erudipwd";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			/*
			 * // Set To: header field of the header.
			 * message.setRecipients(Message.RecipientType.TO,
			 * InternetAddress.parse(to));
			 */

			for (String ccUser : uniqueEmailIdSet) {
				message.addRecipients(Message.RecipientType.TO,
						InternetAddress.parse(ccUser.trim()));
			}

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setContent(msgBody, "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
			
		}
		
		return true;
	}

}
