package com.ewt.dashboardpoint.dashboardpointweb.bb.common;

import java.io.Serializable;



import java.util.Map;

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
public class AuthenticationBB  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1587018746956041871L;
	private static String CLASS_NAME = AuthenticationBB.class.getName();
	private static ILogger logger = new ILogger();
	private UserScreenDisplayData screenDisplayData ;
	private UserRenderer renderer;

	private String textMsg;
	/**
	 * @return the screenDisplayData
	 */
	public UserScreenDisplayData getScreenDisplayData() {
		return screenDisplayData;
	}
	/**
	 * @param screenDisplayData the screenDisplayData to set
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
	 * @param renderer the renderer to set
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
		if ((FacesContext.getCurrentInstance().getViewRoot().getViewId())
				.contains("index.xhtml")) {
			getRenderer().getDataRenderer().setLoginRenderer(true);
		}
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
		getScreenDisplayData().setUserEmailId(getScreenDisplayData().getUserEmailId().toUpperCase());

		if (StringValidator.isBlankOrNull(getScreenDisplayData().getUserEmailId())) {
			logger.debug("UserId is Blank");
			getScreenDisplayData().setError(true);
			getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
			commonErrorRendering();
		}

		if (StringValidator.isBlankOrNull(getScreenDisplayData().getUserPassword())){
			logger.debug("Password is Blank");
			getScreenDisplayData().setError(true);
			getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
			commonErrorRendering();
		}

		if (getScreenDisplayData().isError() == true) {
			logger.debug("Error: UserID & PassWord is Blanck ");
		} else {
			validateUser(getScreenDisplayData().getUserEmailId(),getScreenDisplayData().getUserPassword());
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	public boolean validateUser(String userEmailId, String userPwd){
		final String METHOD_NAME = "validateUser()";
		logger.entering(CLASS_NAME, METHOD_NAME);


		UserDetailBO userDetailBO = new MaintainUserBD().fetchUserByEmailId(userEmailId);
		String deshiperPwd = SecurityUtil.decryptText(userDetailBO.getPassword());

		logger.debug("userId"+userEmailId);
		logger.debug("userPwd "+userPwd);
		logger.debug("userDetailBO.getPassword()"+userDetailBO.getPassword());
		logger.debug("deshiperPwd "+deshiperPwd);
		if (userEmailId.equals(userDetailBO.getUserEmailId()) && userPwd.equals(deshiperPwd)) {


			logger.debug("login Successfull");
			logger.debug("userId after login:"+userEmailId);
			logger.debug("password after login:"+userPwd);

			getScreenDisplayData().setHeaderName(userDetailBO.getFirstName());
			getRenderer().getDataRenderer().setProductNameRendered(true);
			getRenderer().getDataRenderer().setLogoutRenderer(true);// To make Logout link visible in header.
			getRenderer().getDataRenderer().setUserProfileRendered(true);
			getScreenDisplayData().setUserEmailId(userEmailId);
			getScreenDisplayData().setUserPassword(userPwd);

			if(userDetailBO.getUserStatus()!=null && userDetailBO.getUserStatus().equalsIgnoreCase("NTVERIFD")){

				RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
			}else{

				RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/home.faces");
			}

			logger.exiting(CLASS_NAME, METHOD_NAME);
			return true;
		} else { 
			if (userDetailBO.getUserEmailId() != userEmailId.toUpperCase()) {
				logger.debug("Invalid UserID");
				getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
				commonErrorRendering();
			}
			if (userDetailBO.getPassword()!= userPwd) {
				logger.debug("Invalid password");
				getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_USER_ID_OR_INVALID_PASSWORD_MESSAGE);
				commonErrorRendering();
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return false;
	}

	/**
	 * This Method Invokes when user forgot UserId Or PassWord,
	 * This Method send userId Or PassWord to entered valid Email Address 
	 * 
	 * @param String infoNeeded
	 * 
	 * @return void
	 */
	public void forgotIdPasswordAction(String infoNeeded) {
		final String METHOD_NAME = "forgotIdPasswordAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		initializeAllScreenMessages();
		setTextMsg("");
		getScreenDisplayData().getMessage().setScreenResponseMessageForUser(null);
		getScreenDisplayData().setError(false);
		String tmpEmailId = getEmailToBeUsed(infoNeeded);

		if (infoNeeded.equals("userId")) {
			if (StringValidator.isBlankOrNull(getScreenDisplayData().getUserIdEmailAddress())) {
				logger.debug("Email Address is blanck or null");
				getScreenDisplayData().getMessage().setScreenResponseMessageForUser(MessageConstants.ERROR_USER_EMAIL_ADDRESS_BLANK_OR_INVALID);
				commonErrorRendering();
			} else {
				if (EmailUtil.isValidEmailAddress(tmpEmailId)) {
					boolean	isSuccess = new MaintainUserBD().checkIfEmailAddressExist(tmpEmailId);
					if (isSuccess == true) {
						logger.debug("Email Address is Valid");
						EmailUtil.sendEmail(("User Id: " + getScreenDisplayData().getUserEmailId()), tmpEmailId,ErrorMessages.FORGOT_LOGIN_CREDENTIAL_SUBJECT);
						getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.USER_ID_SENT_TO_EMAIL);
						getScreenDisplayData().getMessage().setScreenResponseMessageColor(ApplicationConstants.SUCCESS_MESSAGE_COLOR);
						getRenderer().getDataRenderer().setUserTextRenderer(true);

					} else {
						logger.debug("Email Address is not Valid");
						getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.CONTACT_US_INVALID_EMAIL_ADDRESS);
						commonErrorRendering();
					}
				} else {
					logger.debug("Invalid Email Address");
					getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_EMAIL_ADDRESS_FORMAT);
					commonErrorRendering();
				}

			}
		}

		else if (infoNeeded.equals("password")) {
			if (StringValidator.isBlankOrNull(getScreenDisplayData().getPasswordEmailAddress())) {
				logger.debug("Email Address is blanck or null");
				getScreenDisplayData().setError(true);
				textScreenMsg(MessageConstants.ERROR_USER_EMAIL_ADDRESS_BLANK_OR_INVALID);
				getScreenDisplayData().getMessage().setScreenResponseMessageForUser(getTextMsg());
				commonErrorRendering();
			}

			if (StringValidator.isBlankOrNull(getScreenDisplayData().getPassCheckUserId())) {
				logger.debug("Email Address is blanck or null");
				getScreenDisplayData().setError(true);
				textScreenMsg(MessageConstants.ERROR_USER_PASWWORD_BLANK_OR_INVALID);
				getScreenDisplayData().getMessage().setScreenResponseMessageForUser(getTextMsg());
				commonErrorRendering();
			}

			if (getScreenDisplayData().isError()== true) {

			} else {
				if (EmailUtil.isValidEmailAddress(tmpEmailId)) {
					logger.debug("valid Email Address");
				} else {
					getScreenDisplayData().setError(true);
					logger.debug("Invalid Email Address");
					getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_EMAIL_ADDRESS_FORMAT);
					commonErrorRendering();
				}

				UserDetailBO userDetailBOObject = new MaintainUserBD().fetchUserByEmailId(getScreenDisplayData().getPassCheckUserId());

				if (userDetailBOObject.getUserEmailId().equals("")||userDetailBOObject.getUserEmailId().equals(null)) {
					logger.debug("Entered userID does not exists");
					getScreenDisplayData().setError(true);
					getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.INVALID_USER_ID_MESSAGE);
					commonErrorRendering();
				}
				if (getScreenDisplayData().isError()==true) {
					logger.debug("Email Address & UserId are Not Correct");
				} else {
					if (tmpEmailId.equals(userDetailBOObject.getUserId())) {
						logger.debug("Valid Email Address:");
						EmailUtil.sendEmail(("User Id: " + getScreenDisplayData().getPassCheckUserId()), tmpEmailId,ErrorMessages.FORGOT_LOGIN_CREDENTIAL_SUBJECT);
						getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.USER_ID_SENT_TO_EMAIL);
						getScreenDisplayData().getMessage().setScreenResponseMessageColor(ApplicationConstants.SUCCESS_MESSAGE_COLOR);
						getRenderer().getDataRenderer().setUserTextRenderer(true);
						logger.debug("UserID sent to Email Address Successfully");
					} else {
						logger.debug("Invalid Email Address");
						getScreenDisplayData().getMessage().setScreenResponseMessageForUser(ErrorMessages.CONTACT_US_INVALID_EMAIL_ADDRESS);
						commonErrorRendering();
					}
				}

			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		RedirectScreen
		.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
	}

	/**
	 * This Method set Error Messages
	 * 
	 * @return void
	 */
	public void commonErrorRendering() {
		final String METHOD_NAME = "commonErrorRendering()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		getScreenDisplayData().getMessage().setScreenResponseMessageColor(ApplicationConstants.ERROR_MESSAGE_COLOR);
		getRenderer().getDataRenderer().setUserTextRenderer(true);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method to redirect to appropriate pages on click of links Logout and Login
	 * in header and Cancel button in Login page.
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

			logger.debug("FacesContext.getCurrentInstance().getExternalContext():::::::::::::::::"+FacesContext.getCurrentInstance().getExternalContext());
			RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
		} else if (action.equals("logout")) {

			/* To invalidate the existing session and create a new session */
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();
			FacesContext.getCurrentInstance().getExternalContext()
			.getSession(true);

			getRenderer().getDataRenderer().setLogoutRenderer(false); /* To make Logout link in header invisible */
			getRenderer().getDataRenderer().setLoginRenderer(false); /* To make Login link in header invisible */

			RedirectScreen
			.redirectToPage("/DashBoardPointWeb/index.faces");
		} else if (action.equals("cancel")) {
			getRenderer().getDataRenderer().setLoginRenderer(true);
			//setLogin(true);
			RedirectScreen
			.redirectToPage("/DashBoardPointWeb/index.faces");
		}else if(action.equals("register")){
			RedirectScreen
			.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
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
	private String getEmailToBeUsed(String infoNeeded) {
		if (infoNeeded.equals("userId")) {
			return getScreenDisplayData().getUserIdEmailAddress();
		} else if (infoNeeded.equals("password")) {
			return getScreenDisplayData().getPasswordEmailAddress();
		}

		return "";
	}

	/**
	 * Method for redirecting to addUserDetails page on click of Create New Account link.
	 * 
	 * @return void
	 */
	public void newUserAccount() {
		final String METHOD_NAME = "newUserAccount()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		RedirectScreen
		.redirectToPage("/DashBoardPointWeb/pages/user/userRegistration.faces");
		getRenderer().getDataRenderer().setLoginRenderer(true);
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
	 * @param String textMsg
	 * 
	 * @return void
	 */
	public void textScreenMsg(String textMsg) {
		final String METHOD_NAME = "textScreenMsg()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (textMsg.equals(null)) {
			this.textMsg = textMsg;
		} else
			if (this.textMsg == "") {
				this.textMsg = textMsg;
			} else 
				if (this.textMsg == null) {
					this.textMsg = textMsg;
				}
				else {
					this.textMsg= this.textMsg+ " <br/> " + textMsg;
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
	 * @param textMsg the textMsg to set
	 */
	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

	public void applySecurityRestrictionsToScreenComponents(){

		final String METHOD_NAME = "applySecurityRestrictionsToScreenComponents()";
		logger.entering(CLASS_NAME, METHOD_NAME);


		if(getScreenDisplayData().getUserEmailId()!=null){
			logger.debug("tarang getUserId"+getScreenDisplayData().getUserEmailId());
		}else{
			boolean isValid = false;
			//TODO::integration with dashboard point project getting user name and pwd from url 

			/* FacesContext context = FacesContext.getCurrentInstance();
		        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		        String userId = paramMap.get("userId");
		        String userPwd = paramMap.get("userPwd");
		        logger.debug("tarang123 userId "+userId+"userPwd "+userPwd);
		        if(!(StringValidator.isBlankOrNull(userId))&& !(StringValidator.isBlankOrNull(userPwd))){
		        	logger.debug("request parameters are not null and sent for validation ");
		        	isValid = validateUser(userId.toUpperCase(),userPwd);
		        }*/

			if(!isValid){
				RedirectScreen
				.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
			}

		}


		logger.exiting(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * 
	 */
	public void forgotActionListener(){
		final String METHOD_NAME = "forgotActionListener()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		getScreenDisplayData().setPasswordEmailAddress(getScreenDisplayData().getUserEmailId());
		logger.exiting(CLASS_NAME, METHOD_NAME);

	}

}
