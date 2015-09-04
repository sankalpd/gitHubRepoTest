package com.ewt.dashboardpoint.dashboardpointweb.bb.common;

import java.io.Serializable;
import java.math.BigInteger;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import com.ewt.dashboardpoint.dashboardpointweb.bb.user.bo.ScreenResponseMessageBO;
import com.ewt.dashboardpoint.dashboardpointweb.bb.user.bo.UserScreenDisplayData;
import com.ewt.dashboardpoint.dashboardpointweb.bb.util.RedirectScreen;
import com.ewt.dashboardpoint.dashboardpointweb.bo.user.UserRenderer;
import com.ewt.dashboardpoint.dashboardpointweb.bd.MaintainUserBD;
import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.dashboardpoint.framework.email.EmailUtil;
import com.ewt.dashboardpoint.framework.messages.ErrorMessages;
import com.ewt.dashboardpoint.framework.messages.MessageConstants;
import com.ewt.dashboardpoint.framework.password.SecurityUtil;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;

import com.ewt.erudiutil.validator.StringValidator;
import com.ewt.framework.logging.ILogger;

@ManagedBean
@SessionScoped
public class AuthenticationBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1587018746956041871L;
	private static String CLASS_NAME = AuthenticationBB.class.getName();
	private static ILogger logger = new ILogger();
	private UserScreenDisplayData screenDisplayData;
	private UserRenderer renderer;

	private String textMsg;

	/**
	 * @return the screenDisplayData
	 */
	public UserScreenDisplayData getScreenDisplayData() {
		return screenDisplayData;
	}

	/**
	 * @param screenDisplayData
	 *            the screenDisplayData to set
	 */
	public void setScreenDisplayData(UserScreenDisplayData screenDisplayData) {
		this.screenDisplayData = screenDisplayData;
	}

	/**
	 * @return the renderer
	 */
	public UserRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @param renderer
	 *            the renderer to set
	 */
	public void setRenderer(UserRenderer renderer) {
		this.renderer = renderer;
	}

	public AuthenticationBB() {
		logger.entering(CLASS_NAME);

		logger.exiting(CLASS_NAME);
	}
	

	@PostConstruct
	public void init() {
		final String METHOD_NAME = "init()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserScreenDisplayData screenDisplayData = new UserScreenDisplayData();
		UserRenderer renderer = new UserRenderer();
		setScreenDisplayData(screenDisplayData);
		setRenderer(renderer);

		/* To render Login link in Index page */
		if ((FacesContext.getCurrentInstance().getViewRoot().getViewId()).contains("index.xhtml")) {
			getRenderer().getDataRenderer().setLoginRenderer(true);
			getRenderer().getDataRenderer().setMetronicLoginRenderer(true);
		}
		getRenderer().getDataRenderer().setloginFormRenderer(true);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	
	/**
	 * Method to set the rendering of the components
	 * 
	 * @return void
	 */
	public void setRendering() {
		final String METHOD_NAME = "setRendering()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		getRenderer().getDataRenderer().setloginFormRenderer(true);
		getRenderer().getDataRenderer().setForgotPwdEnterEmailPanelRenderer(false);
		getRenderer().getDataRenderer().setVerificationPanelRenderer(false);
		getRenderer().getDataRenderer().setChangePasswordRenderer(false);
		getRenderer().getMessageRenderer().setSuccessfulChangedPasswordrenderer(false);
		getScreenDisplayData().setVerificationCode("");
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * Method to redirect on the Registration page
	 * 
	 * @return void
	 */
	public void redirectToMetronicRegisterPage() {
		final String METHOD_NAME = "redirectToMetronicRegisterPage()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/user/metronic_userRegistration.faces");
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}


	/**
	 * Method to validate login credentials and to redirect to corresponding
	 * pages.
	 * 
	 * @return void
	 */
	public void logInAction() {
		final String METHOD_NAME = "logInAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		initializeAllScreenMessages();
		getScreenDisplayData().setError(false);
		getScreenDisplayData().getMessage().setScreenResponseMessageForUser("");
		getScreenDisplayData().setUserEmailId(getScreenDisplayData().getUserEmailId());

		if (StringValidator.isBlankOrNull(getScreenDisplayData()
				.getUserEmailId())) {
			logger.debug("UserId is Blank");
			getScreenDisplayData().setError(true);
			getScreenDisplayData()
					.getMessage()
					.setScreenResponseMessageForUser(
							ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
			commonErrorRendering();
		}

		if (StringValidator.isBlankOrNull(getScreenDisplayData()
				.getUserPassword())) {
			logger.debug("Password is Blank");
			getScreenDisplayData().setError(true);
			getScreenDisplayData()
					.getMessage()
					.setScreenResponseMessageForUser(
							ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
			commonErrorRendering();
		}

		if (getScreenDisplayData().isError() == true) {
			logger.debug("Error: UserID & PassWord is Blank ");
		} else {
			validateUser(getScreenDisplayData().getUserEmailId(),
					getScreenDisplayData().getUserPassword());
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method check if user/email id is already present in database or not
	 * 
	 * @return
	 */
	public boolean validateUser(String userEmailId, String userPwd) {
		final String METHOD_NAME = "validateUser()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserDetailBO userDetailBO = new MaintainUserBD()
				.fetchUserByEmailId(userEmailId);
		
		String deshiperPwd = SecurityUtil.decryptText(userDetailBO
				.getPassword());

		logger.debug("userId" + userEmailId);
		logger.debug("userPwd " + userPwd);
		logger.debug("userDetailBO.getPassword()" + userDetailBO.getPassword());
		logger.debug("deshiperPwd " + deshiperPwd);
		if (userEmailId.toUpperCase().trim()
				.equals(userDetailBO.getUserEmailId().toUpperCase().trim())
				&& userPwd.toUpperCase().trim()
						.equals(deshiperPwd.toUpperCase().trim())) {

			logger.debug("login Successfull");
			logger.debug("userId after login:" + userEmailId);
			logger.debug("password after login:" + userPwd);

			getScreenDisplayData().setHeaderName(userDetailBO.getFirstName());
			getRenderer().getDataRenderer().setProductNameRendered(true);
			getRenderer().getDataRenderer().setLoginRenderer(false);// To make Login link invisible in header. 
			getRenderer().getDataRenderer().setMetronicLoginRenderer(false);// To make Metronic Login link invisible in header.
			getRenderer().getDataRenderer().setLogoutRenderer(true);// To make Logout link visible in header.
			getRenderer().getDataRenderer().setUserProfileRendered(true);
			getScreenDisplayData().setUserEmailId(userEmailId);
			getScreenDisplayData().setUserPassword(userPwd);

			if (userDetailBO.getUserStatus() != null
					&& userDetailBO.getUserStatus()
							.equalsIgnoreCase(ApplicationConstants.VERIFICATION_PENDING_USER_STATUS)) {
				//if(){
					
					RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
					
				/*}else{
					
					RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/user/metronic_userRegistration.faces");
				}*/
			} else {

				RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/home.faces");
			}

			logger.exiting(CLASS_NAME, METHOD_NAME);
			return true;
		} else {
			if (userDetailBO.getUserEmailId() != userEmailId.toUpperCase()) {
				logger.debug("Invalid UserID");
				getScreenDisplayData()
						.getMessage()
						.setScreenResponseMessageForUser(
								ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
				commonErrorRendering();
			}
			if (userDetailBO.getPassword() != userPwd) {
				logger.debug("Invalid password");
				getScreenDisplayData()
						.getMessage()
						.setScreenResponseMessageForUser(
								ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
				commonErrorRendering();
			}

		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return false;
	}

	/**
	 * This Method has logic to perform Forgot Password Action
	 * 
	 * @return void;
	 */
	public void forgotPasswordListerner() {
		final String METHOD_NAME = "forgotPasswordListerner()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.debug("=================="
				+ getScreenDisplayData().getUserEmailId());
		// Getting EmailId from Login page to Forgot password page
		getScreenDisplayData().setPasswordEmailAddress(
				getScreenDisplayData().getUserEmailId());
		
		getRenderer().getDataRenderer().setChangePasswordRenderer(false);
		getRenderer().getDataRenderer().setVerificationPanelRenderer(false);
		getRenderer().getDataRenderer().setForgotPwdEnterEmailPanelRenderer(
				true);
		getRenderer().getMessageRenderer()
				.setSuccessfulChangedPasswordrenderer(false);
		getRenderer().getDataRenderer().setloginFormRenderer(false);
		logger.exiting(CLASS_NAME, METHOD_NAME);

	}
	/**
	 * This Method has logic to perform forgot password Action This method also
	 * send verification mail to new user with verification code.
	 * 
	 * @return void;
	 */
	public void forgotPasswordAction() {
		final String METHOD_NAME = "forgotPasswordAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		initializeAllScreenMessages();


		getRenderer().getMessageRenderer()
				.setInvalidUserEmailIDForgotPanelRenderer(false);
		
		String EmailId = getScreenDisplayData().getPasswordEmailAddress();
		UserDetailBO userDetailBO = new UserDetailBO();
		//Checking whether user entered email id or not
		if (getScreenDisplayData().getPasswordEmailAddress() != null) {

			String userVerificationKey = createVerificationKey();
			//Checking whether format of Email Id is valid or not
			if (EmailUtil.isValidEmailAddress(EmailId)) {
				//Checking whether User Entered Email Id is present in Database or not.
				boolean isValid = new MaintainUserBD()
						.checkIfEmailAddressExist(EmailId);
				//If EmailId is present in Database then redirect to verification panel from Email Id panel
				if (isValid == true) {
					//fetch user by email id
					logger.debug("Email Address is Valid");
					userDetailBO = new MaintainUserBD()
							.fetchUserByEmailId(getScreenDisplayData()
									.getPasswordEmailAddress());
					userDetailBO.setUserVerificationKey(userVerificationKey);
					sendVerificationMail(userDetailBO);
					

					getRenderer().getMessageRenderer()
							.setInvalidUserEmailIDForgotPanelRenderer(false);
					getRenderer().getDataRenderer()
							.setVerificationPanelRenderer(true);
					
					logger.debug("Email Address "
							+ userDetailBO.getUserEmailId());
					logger.debug("First Name " + userDetailBO.getFirstName());
					logger.debug("User Password " + userDetailBO.getPassword());
					
					//set selected user to screen display data for future reference
					getScreenDisplayData().setRegistedUser(userDetailBO);

				}else {
					logger.debug("Email Address is not Valid");
					getRenderer().getMessageRenderer()
							.setInvalidUserEmailIDForgotPanelRenderer(true);
					getScreenDisplayData().getMessage()
							.setScreenResponseMessageColor("color: red;");
					getScreenDisplayData().getMessage()
							.setScreenResponseErrorMessageForUser(
									ErrorMessages.INVALID_EMAIL_ADDRESS);
					

				}

			}else {
				logger.debug("Email Address is not Valid");
				getRenderer().getMessageRenderer()
						.setInvalidUserEmailIDForgotPanelRenderer(true);
				getScreenDisplayData().getMessage()
						.setScreenResponseMessageColor("color: red;");
				getScreenDisplayData().getMessage()
						.setScreenResponseErrorMessageForUser(
								ErrorMessages.INVALID_EMAIL_ADDRESS);
			
			}
		}else {
			getRenderer().getMessageRenderer()
					.setInvalidUserEmailIDForgotPanelRenderer(true);
			getScreenDisplayData().getMessage().setScreenResponseMessageColor(
					"color: red;");
			getScreenDisplayData().getMessage()
					.setScreenResponseErrorMessageForUser(
							ErrorMessages.INVALID_EMAIL_ADDRESS);
			logger.debug("Please Enter Email Address");
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method draft and send verification mail to registered user
	 * 
	 * @param userDetailBO
	 */

	private void sendVerificationMail(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "sendVerificationMail(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);

		// draft email contant :start
		String msgBody = "<p style=\"mso-line-height-alt:0;line-height:0;\"><h4><strong>Verification Mail</strong></h4>"
				.concat("\n");
		msgBody = msgBody + "<h4>Dear : "
				+ userDetailBO.getFirstName().concat("</h4>\n");
		msgBody = msgBody.concat("<h4>"
				+ "Thank you for your interest in DashBoardPoint.</h4>\n");
		msgBody = msgBody.concat("<h4>" + "Your Email Verification Code is : "
				+ userDetailBO.getUserVerificationKey() + "</h4>\n");
		msgBody = msgBody.concat("<h4><i>" + "Please login.</i>").concat("\n");


		// set email subject
		String emailSubject = "DashBoardPoint: Email Verification";

		// sending email
		boolean isSuccessfull = EmailUtil.sendEmail(msgBody,
				Arrays.asList(userDetailBO.getUserEmailId().trim()),
				emailSubject);

		if (isSuccessfull) {
			logger.debug("Email sent successfully");
			getRenderer().getDataRenderer()
					.setForgotPwdEnterEmailPanelRenderer(false);
			getRenderer().getDataRenderer().setVerificationPanelRenderer(true);

			getRenderer().getDataRenderer()
					.setSuccessfullDataAddForgotPasswordRenderer(true);
			getRenderer().getDataRenderer().setChangePasswordRenderer(false);

		} else {
			logger.debug("Sending mail failed");
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * Verify User Email by comparing verification code entered by user.
	 */
	public void verifyUserEmailForgotPasswordAction() {
		final String METHOD_NAME = "verifyUserEmailForgotPasswordAction";
		logger.entering(CLASS_NAME, METHOD_NAME);

		getScreenDisplayData().getMessage()
				.setScreenResponseVerificationMessageForUser("");
		getRenderer().getMessageRenderer()
				.setInvalidVerificationCodeForgotPanelRenderer(false);
		getRenderer().getMessageRenderer()
				.setSuccessfulChangedPasswordrenderer(false);
		//Comparing verification key
		if (getScreenDisplayData().getVerificationCode().equals(
				getScreenDisplayData().getRegistedUser()
						.getUserVerificationKey())) {
			logger.debug("verification successfull");
			// change status of registered user:start

			UserDetailBO userDetailBO = getScreenDisplayData()
					.getRegistedUser();
			MaintainUserBD userBD = new MaintainUserBD();
			// update database
			logger.debug("UserID in BB getUserEmailId::::::::::"
					+ userDetailBO.getUserEmailId());
			userDetailBO = userBD.fetchUserByEmailId(userDetailBO
					.getUserEmailId());
			logger.debug("UserID in BB getUserId::::::::::"
					+ userDetailBO.getUserId());
			userDetailBO.setUserStatus(ApplicationConstants.USER_ACTIVE_STATUS);

			boolean isSuccessfull = userBD.updateUser(userDetailBO);
			// change status of registered user:end
			if (isSuccessfull) {
				logger.debug("user status changed successfully");
				getRenderer().getDataRenderer().setVerificationPanelRenderer(
						false);
				getRenderer().getDataRenderer().setChangePasswordRenderer(true);
				getRenderer().getDataRenderer()
						.setForgotPwdEnterEmailPanelRenderer(false);

				getScreenDisplayData().getMessage()
						.setScreenResponseMessageColor("color: blue;");
				getScreenDisplayData().getMessage()
						.setScreenResponseVerificationMessageForUser(
								MessageConstants.USER_VERIFICATION_SUCCESSFULL);

			} else {
				getRenderer().getMessageRenderer()
						.setInvalidVerificationCodeForgotPanelRenderer(true);
				logger.debug("some error occured while changing user status");

				getScreenDisplayData().getMessage()
						.setScreenResponseMessageColor("color: red;");

				getScreenDisplayData().getMessage()
						.setScreenResponseVerificationMessageForUser(
								MessageConstants.USER_VERIFICATION_FAILED);

			}

		} else {
			getRenderer().getMessageRenderer()
					.setInvalidVerificationCodeForgotPanelRenderer(true);
			logger.debug("verification failed");
			getScreenDisplayData().getMessage().setScreenResponseMessageColor(
					"color: red;");
			getScreenDisplayData().getMessage()
					.setScreenResponseVerificationMessageForUser(
							MessageConstants.USER_VERIFICATION_FAILED);
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method is used to change password of user
	 */
	public void changePasswordAction() {
		final String METHOD_NAME = "changePasswordAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		MaintainUserBD userBD = new MaintainUserBD();
		getRenderer().getDataRenderer().setChangePasswordRenderer(true);
		getRenderer().getDataRenderer().setVerificationPanelRenderer(false);
		getScreenDisplayData().getMessage()
				.setScreenResponseChangePasswordMessageForUser("");
		getRenderer().getMessageRenderer()
				.setInvalidChangePasswordForgotPanelRenderer(false);
		getRenderer().getDataRenderer().setForgotPwdEnterEmailPanelRenderer(
				false);

		getRenderer().getMessageRenderer()
				.setSuccessfulChangedPasswordrenderer(false);
		getScreenDisplayData().getMessage()
				.setScreenResponseSuccessfullChangedPasswordMessageForUser("");
		UserDetailBO userDetailBO = new UserDetailBO();
		//Checking whether user entered password and confirm password or not
		if (StringValidator.isBlankOrNull(getScreenDisplayData()
				.getChangePassword())
				&& StringValidator.isBlankOrNull(getScreenDisplayData()
						.getChangeConfirmPassword())) {

			getRenderer().getMessageRenderer()
					.setInvalidChangePasswordForgotPanelRenderer(true);
			getScreenDisplayData().getMessage().setScreenResponseMessageColor(
					"color: red;");
			getScreenDisplayData()
					.getMessage()
					.setScreenResponseChangePasswordMessageForUser(
							MessageConstants.ERROR_NEW_USER_PASWWORD_BLANK_OR_INVALID);

		}

		else {
			//Checking whether user entered new password and confirm password is valid or not
			boolean isValid = validateRegisterNewUserScreenInputData();

			if (isValid == true) {

				getRenderer().getDataRenderer()
						.setForgotPwdEnterEmailPanelRenderer(false);
				getRenderer().getDataRenderer()
						.setChangePasswordRenderer(false);
				getRenderer().getDataRenderer().setVerificationPanelRenderer(
						false);
				getRenderer().getMessageRenderer()
						.setSuccessfulChangedPasswordrenderer(true);
				
				getScreenDisplayData().setUserPassword(
						getScreenDisplayData().getChangePassword());

				userDetailBO = getScreenDisplayData().getRegistedUser();
				String cipherText = encryptPassword(getScreenDisplayData()
						.getUserPassword());
				userDetailBO.setPassword(cipherText);

				userBD.updateUser(userDetailBO);// Update password
				getScreenDisplayData().getMessage()
						.setScreenResponseMessageColor(
								"color: blue; float: center;");
				getScreenDisplayData()
						.getMessage()
						.setScreenResponseSuccessfullChangedPasswordMessageForUser(
								MessageConstants.CHANGED_PASSWORD_SUCCESSFULL);
			}

		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method is used to Encrypt the password
	 */
	private String encryptPassword(String password) {
		final String METHOD_NAME = "persistUserDetails";
		logger.entering(CLASS_NAME, METHOD_NAME);
		String cipherText = SecurityUtil.encryptText(password);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return cipherText;

	}

	/**
	 * Business Rule Validate registration form fields, verify user has entered
	 * all mandatory form field in valid format.
	 * 
	 * @return
	 */
	private boolean validateRegisterNewUserScreenInputData() {
		final String METHOD_NAME = "validateRegisterNewUserScreenInputData";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isValid = true;

		isValid = isValid && checkPasswordAndConfirmPasswordMatches();

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isValid;
	}

	/**
	 * This method confirm that user has entered valid password by c
	 * 
	 * @return
	 */
	private boolean checkPasswordAndConfirmPasswordMatches() {
		final String METHOD_NAME = "checkPasswordAndConfirmPasswordMatches";
		logger.entering(CLASS_NAME, METHOD_NAME);

		getRenderer().getDataRenderer().setVerificationPanelRenderer(false);
		getRenderer().getDataRenderer().setForgotPwdEnterEmailPanelRenderer(
				false);
		getRenderer().getDataRenderer().setChangePasswordRenderer(true);

		getScreenDisplayData().getMessage()
				.setScreenResponseChangePasswordMessageForUser("");
		getRenderer().getMessageRenderer()
				.setInvalidChangePasswordForgotPanelRenderer(false);
		boolean isValid = true;
		// check if user entered same password and confirm password
		if (getScreenDisplayData().getChangePassword().equals(
				getScreenDisplayData().getChangeConfirmPassword())) {
			logger.debug("user entered correct password");
		} else {
			isValid = false;
			logger.debug("user entered incorrect password");
			getRenderer().getMessageRenderer()
					.setInvalidChangePasswordForgotPanelRenderer(true);
			
			getScreenDisplayData().getMessage().setScreenResponseMessageColor(
					"color: red;");
			getScreenDisplayData().getMessage()
					.setScreenResponseChangePasswordMessageForUser(
							ErrorMessages.INVALID_CHECK_PASSWORD_MESSAGE);
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isValid;

	}

	

	/**
	 * Creating Verification Key
	 * 
	 * @return String
	 */
	private String createVerificationKey() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32).substring(0, 6)
				.toUpperCase();

	}

	/**
	 * This Method set Error Messages
	 * 
	 * @return void
	 */
	public void commonErrorRendering() {
		final String METHOD_NAME = "commonErrorRendering()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		getScreenDisplayData().getMessage().setScreenResponseMessageColor(
				ApplicationConstants.ERROR_MESSAGE_COLOR);
		getRenderer().getDataRenderer().setUserTextRenderer(true);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method to redirect to appropriate pages on click of links Logout and
	 * Login in header and Cancel button in Login page.
	 * 
	 * @param action
	 * 
	 * @return void
	 */
	public void logOutCancelAction(String action) {
		final String METHOD_NAME = "logOutCancelAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (action.equals("login")) {
			
			/* To invalidate the existing session and create a new session */
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSession(true);

			logger.debug("FacesContext.getCurrentInstance().getExternalContext():::::::::::::::::"
					+ FacesContext.getCurrentInstance().getExternalContext());
			RedirectScreen
					.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
			
		}else if (action.equals("metronic_login")){
			
			/* To invalidate the existing session and create a new session */
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();
			FacesContext.getCurrentInstance().getExternalContext()
			.getSession(true);

			logger.debug("FacesContext.getCurrentInstance().getExternalContext():::::::::::::::::"+FacesContext.getCurrentInstance().getExternalContext());
			RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/authentication/metronic_login.faces");
		}
		else if (action.equals("logout")) {

			/* To invalidate the existing session and create a new session */
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSession(true);

			getRenderer().getDataRenderer().setLogoutRenderer(false); /* To make Logout link in header invisible */
			getRenderer().getDataRenderer().setLoginRenderer(false); /* To make Login link in header invisible */

			RedirectScreen.redirectToPage("/DashBoardPointWeb/index.faces");
		} else if (action.equals("cancel")) {
			getRenderer().getDataRenderer().setLoginRenderer(true);
			getRenderer().getDataRenderer().setMetronicLoginRenderer(true);
			//setLogin(true);
			RedirectScreen.redirectToPage("/DashBoardPointWeb/index.faces");
		}else if(action.equals("register")){
			//RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
			RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method for redirecting to corresponding pages on click of Header Image.
	 * 
	 * @return void
	 */
	public void headerAction() {
		final String METHOD_NAME = "headerAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if ((FacesContext.getCurrentInstance().getViewRoot().getViewId())
				.contains("login.xhtml")) {
			getRenderer().getDataRenderer().setLoginRenderer(true);
			getRenderer().getDataRenderer().setMetronicLoginRenderer(true);			
			getRenderer().getDataRenderer().setLogoutRenderer(false);
			RedirectScreen
					.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
		} else {
			RedirectScreen
					.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method for getting the corresponding Email Address for Passed info.
	 * 
	 * @param infoNeeded
	 * 
	 * @return void
	 */

	/**
	 * Method for redirecting to addUserDetails page on click of Create New
	 * Account link.
	 * 
	 * @return void
	 */
	public void newUserAccount() {
		final String METHOD_NAME = "newUserAccount()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		RedirectScreen
				.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
		getRenderer().getDataRenderer().setLoginRenderer(true);
		getRenderer().getDataRenderer().setMetronicLoginRenderer(true);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method Initializing Screen Messages.
	 * 
	 * @return void
	 */
	public void initializeAllScreenMessages() {
		final String METHOD_NAME = "initializeAllScreenMessages()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		getScreenDisplayData().setMessage(new ScreenResponseMessageBO());
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method use for showing multiple Messages on the Screen.
	 * 
	 * @param String
	 *            textMsg
	 * 
	 * @return void
	 */
	public void textScreenMsg(String textMsg) {
		final String METHOD_NAME = "textScreenMsg()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (textMsg.equals(null)) {
			this.textMsg = textMsg;
		} else if (this.textMsg == "") {
			this.textMsg = textMsg;
		} else if (this.textMsg == null) {
			this.textMsg = textMsg;
		} else {
			this.textMsg = this.textMsg + " <br/> " + textMsg;
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * @return the textMsg
	 */
	public String getTextMsg() {
		return textMsg;
	}

	/**
	 * @param textMsg
	 *            the textMsg to set
	 */
	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

	public void applySecurityRestrictionsToScreenComponents() {

		final String METHOD_NAME = "applySecurityRestrictionsToScreenComponents()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (getScreenDisplayData().getUserEmailId() != null) {
			logger.debug("tarang getUserId"
					+ getScreenDisplayData().getUserEmailId());
		} else {
			boolean isValid = false;
			// TODO::integration with dashboard point project getting user name
			// and pwd from url

			/*
			 * FacesContext context = FacesContext.getCurrentInstance();
			 * Map<String, String> paramMap =
			 * context.getExternalContext().getRequestParameterMap(); String
			 * userId = paramMap.get("userId"); String userPwd =
			 * paramMap.get("userPwd");
			 * logger.debug("tarang123 userId "+userId+"userPwd "+userPwd);
			 * if(!(StringValidator.isBlankOrNull(userId))&&
			 * !(StringValidator.isBlankOrNull(userPwd))){ logger.debug(
			 * "request parameters are not null and sent for validation ");
			 * isValid = validateUser(userId.toUpperCase(),userPwd); }
			 */

			if (!isValid) {
				RedirectScreen
						.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
			}

		}

		logger.exiting(CLASS_NAME, METHOD_NAME);

	}
}
