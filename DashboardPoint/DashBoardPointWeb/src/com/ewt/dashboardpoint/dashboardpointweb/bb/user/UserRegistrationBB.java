package com.ewt.dashboardpoint.dashboardpointweb.bb.user;

import java.io.Serializable;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ewt.dashboardpoint.dashboardpointweb.bb.common.AuthenticationBB;
import com.ewt.dashboardpoint.dashboardpointweb.bb.user.bo.ScreenResponseMessageBO;
import com.ewt.dashboardpoint.dashboardpointweb.bb.user.bo.UserScreenDisplayData;
import com.ewt.dashboardpoint.dashboardpointweb.bb.util.RedirectScreen;
import com.ewt.dashboardpoint.dashboardpointweb.bo.user.UserRenderer;
import com.ewt.dashboardpoint.dashboardpointweb.bd.MaintainUserBD;
import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.dashboardpoint.framework.email.EmailUtil;
import com.ewt.dashboardpoint.framework.messages.ErrorMessages;
import com.ewt.dashboardpoint.framework.messages.MessageConstants;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.erudiutil.validator.StringValidator;
import com.ewt.framework.logging.ILogger;

/**
 * Viewed scope managed backing bean used for user registration. This class is
 * responsible for registration and verification of user.
 */
@ManagedBean
@ViewScoped
public class UserRegistrationBB implements Serializable {

	/**
	 * auto generated serial version U
	 */
	private static final long serialVersionUID = 3395324643598751000L;

	/**
	 * class name string variable
	 */
	private static String CLASS_NAME = UserRegistrationBB.class.getName();

	/**
	 * logger class level variable for performing logging
	 */
	private static ILogger logger = new ILogger();

	/**
	 * screen display data used for displaying screen values
	 */
	private UserScreenDisplayData screenDisplayData;

	/**
	 * screen component renderer used for rendering screen components
	 */
	private UserRenderer renderer;
	
	
	@ManagedProperty(value="#{authenticationBB}")
	private AuthenticationBB  sessionBean;

	
	
	/**
	 * @return the sessionBean
	 */
	public AuthenticationBB getSessionBean() {
		return sessionBean;
	}

	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(AuthenticationBB sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * Default constructor for managed Bean
	 */
	public UserRegistrationBB() {
		logger.entering(CLASS_NAME);

		logger.exiting(CLASS_NAME);
	}

	/**
	 * This method is used to initialize the screen components
	 */
	@PostConstruct
	public void init() {
		final String METHOD_NAME = "init()";
		logger.entering(CLASS_NAME, METHOD_NAME);

		//setting screen display data
		UserScreenDisplayData screenDisplayData = new UserScreenDisplayData();
		setScreenDisplayData(screenDisplayData);
		//setting renderer
		UserRenderer renderer = new UserRenderer();
		setRenderer(renderer);

		getRenderer().getDataRenderer().setVerificationPanel(false);
		getRenderer().getDataRenderer().setSuccessfullDataAddPanelRenderer(false);
		getRenderer().getDataRenderer().setVerificationPanel(false);
		
		
		if(getSessionBean()!=null && getSessionBean().getScreenDisplayData()!=null ){
			if(getSessionBean().getScreenDisplayData().getUserEmailId()!=null && getSessionBean().getScreenDisplayData().getUserEmailId().length()>0) {
				UserDetailBO userDetailBO =  new MaintainUserBD().fetchUserByEmailId(getSessionBean().getScreenDisplayData().getUserEmailId());
				if(userDetailBO!=null && userDetailBO.getUserStatus()!=null && userDetailBO.getUserStatus().equals("NTVERIFD")){
					getRenderer().getDataRenderer().setVerificationPanel(true);
					getRenderer().getDataRenderer().setVerificationHomeScreenPanel(true);
					getScreenDisplayData().setRegistedUser(userDetailBO);
				}
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This Method has logic to perform validation and persist user detail into
	 * database. This method also send verification mail to new user with verification 
	 * code. 
	 * @return void;
	 */
	public void registerUserAction() {
		final String METHOD_NAME = "registerUserAction()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		//initialize all screen response messages
		initializeAllScreenMessages();

		//validate registration form fields
		boolean isValid = validateRegisterNewUserScreenInputData();

		if(!isValid){
			logger.debug("form fields are not valid");
			return;
		}else{
			//persist new user
			registerUser();

		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	
	/**
	 * This method contains logic for persisting user details in database if its not already there.
	 */
	private void registerUser() {
		final String METHOD_NAME = "registerUser";
		logger.entering(CLASS_NAME, METHOD_NAME);


		//create User Detail BO from screen display data:start
		UserDetailBO userDetailBO = new UserDetailBO();
		userDetailBO.setUserEmailId(getScreenDisplayData().getUserEmailId().toUpperCase());
		userDetailBO.setFirstName(getScreenDisplayData().getFirstName());
		userDetailBO.setLastName(getScreenDisplayData().getLastName());
		userDetailBO.setPassword(getScreenDisplayData().getUserPassword());
		userDetailBO.setUserStatus(ApplicationConstants.VERIFICATION_PENDING_USER_STATUS);

		MaintainUserBD  userBD = new MaintainUserBD();
		boolean isProcessSuccessful = userBD.registerUserInPendedState(userDetailBO);


		if(isProcessSuccessful){				
			//set userDetailBO in screen screen display data for verification 
			getScreenDisplayData().setRegistedUser(userDetailBO);
			//set verification panel renderer true
			getRenderer().getDataRenderer().setVerificationPanel(true);

		}else{
			logger.debug("user addition into the database fails");
			getScreenDisplayData().getMessage().setInvalidUserEmailIDMsg(ErrorMessages.INVALID_USER_ALREADY_EXISTS_MESSAGE);
				
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method draft and send verification mail to registered user
	 * @param userDetailBO
	 */
	private void sendVerificationMail(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "sendVerificationMail(UserDetailBO userDetailBO)";
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
		
		if(isSuccessfull){
			logger.debug("Email sent successfully");
			getRenderer().getDataRenderer().setVerificationPanel(true);
			getRenderer().getDataRenderer().setSuccessfullDataAddPanelRenderer(false);
		}else{
			logger.debug("Sending mail failed");
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);

	}
	
	/**
	 * This method resend verification mail to registered user
	 */

	public void resend() {
		final String METHOD_NAME = "send(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserDetailBO userDetailBO = getScreenDisplayData().getRegistedUser();
		sendVerificationMail(userDetailBO);
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage("Successful",  "Verification Code ReSend Successfully" ));
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * Business Rule
	 * Validate registration form fields, verify user has entered all 
	 * mandatory form field in valid format. 
	 * @return
	 */
	private boolean validateRegisterNewUserScreenInputData() {
		final String METHOD_NAME = "validateRegisterNewUserScreenInputData";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isValid =  true;
		isValid = isValid && validateMandatoryDataIsEntered();
		isValid = isValid && checkIfUserIDEmailFormatIsValid();
		isValid = isValid && checkPasswordAndConfirmPasswordMatches();

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isValid;
	}


	/**
	 * validate mandatory data is entered 
	 * @return true if valid, false if invalid
	 * 
	 */
	private boolean validateMandatoryDataIsEntered() {
		final String METHOD_NAME = "validateRegisterNewUserScreenInputData";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isValid = true;

		//check if User Id is entered or not
		if(StringValidator.isBlankOrNull(getScreenDisplayData().getUserEmailId())){
			isValid = false;
			getScreenDisplayData().getMessage().setInvalidUserEmailIDMsg(ErrorMessages.INVALID_USER_ID_MESSAGE);
		}

		//check if User has entered password
		if(StringValidator.isBlankOrNull(getScreenDisplayData().getUserPassword())){
			isValid = false;
			getScreenDisplayData().getMessage().setInvalidUserPasswordMsg(ErrorMessages.INVALID_PASSWORD_MESSAGE);
		}

		//check if User has entered confirm password
		if(StringValidator.isBlankOrNull(getScreenDisplayData().getReEnteredPassword())){
			isValid = false;
			getScreenDisplayData().getMessage().setInvalidUserConfirmPasswordMsg(ErrorMessages.INVALID_CONFIRM_PASSWORD_MESSAGE);
		}

		//check if User has entered first Name
		if(StringValidator.isBlankOrNull(getScreenDisplayData().getFirstName())){
			isValid = false;
			getScreenDisplayData().getMessage().setInvalidUserFirstNameMsg(ErrorMessages.INVALID_FIRST_NAME_MESSAGE);
		}

		//check if User has entered last Name
		if(StringValidator.isBlankOrNull(getScreenDisplayData().getLastName())){
			isValid = false;
			getScreenDisplayData().getMessage().setInvalidUserLastNameMsg(ErrorMessages.INVALID_LAST_NAME_MESSAGE);
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isValid;

	}


	/**
	 * This method will check if entered userID/emailID is valid or not
	 * @return true if valid, false if invalid
	 */
	private boolean checkIfUserIDEmailFormatIsValid() {
		final String METHOD_NAME = "checkIfUserIDEmailFormatIsValid";
		logger.entering(CLASS_NAME, METHOD_NAME);

		boolean isValid =  true;

		if (EmailUtil.isValidEmailAddress(getScreenDisplayData().getUserEmailId())) {			
			logger.debug("valid Email Address");
		}
		else {
			logger.debug("In valid Email Address"+getScreenDisplayData().getUserEmailId());
			isValid= false;
			getScreenDisplayData().getMessage().setInvalidUserEmailIDMsg(ErrorMessages.INVALID_EMAIL_ADDRESS_FORMAT);
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isValid;
	}


	/**
	 * This method confirm that user has entered valid password by c
	 * @return
	 */
	private boolean checkPasswordAndConfirmPasswordMatches() {
		final String METHOD_NAME = "checkPasswordAndConfirmPasswordMatches";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isValid = true;
		//check if user entered same password and confirm password
		if(getScreenDisplayData().getUserPassword().equals(getScreenDisplayData().getReEnteredPassword())){

			logger.debug("user entered correct password");
		}else{
			isValid =  false;
			logger.debug("user entered incorrect password");
			//getScreenDisplayData().getMessage().setInvalidUserConfirmPasswordMsg(ErrorMessages.INVALID_CHECK_PASSWORD_MESSAGE);
			getScreenDisplayData().getMessage().setInvalidUserPasswordMsg(ErrorMessages.INVALID_CHECK_PASSWORD_MESSAGE);
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isValid;

	}
	

	/**
	 * Verify User Email by comparing verification code entered by user.
	 */
	public void verifyUserEmailAction(){
		final String METHOD_NAME = "verifyUserEmailAction";
		logger.entering(CLASS_NAME, METHOD_NAME);
		FacesContext context = FacesContext.getCurrentInstance();	
		//getting current user details from the screen display data
		UserDetailBO userDetailBO = getScreenDisplayData().getRegistedUser();

		//setting entered verification key to the user detail BO
		userDetailBO.setUserVerificationKey(getScreenDisplayData().getVerificationCode());

		//verify user
		boolean isVerificationSuccessful = new MaintainUserBD().verifyUser(userDetailBO);

		//change status of registered user:end
		if(isVerificationSuccessful){
			logger.debug("user status changed successfully");
			getRenderer().getDataRenderer().setSuccessfullDataAddPanelRenderer(true);
			getScreenDisplayData().getMessage().setScreenResponseMessageColor("color: blue;");
			getScreenDisplayData().getMessage().setScreenResponseMessageForUser(MessageConstants.USER_VERIFICATION_SUCCESSFULL);
			context.addMessage(null, new FacesMessage("Successful",MessageConstants.USER_VERIFICATION_SUCCESSFULL));
		}else{
			logger.debug("some error occured while changing user status");
			getRenderer().getDataRenderer().setSuccessfullDataAddPanelRenderer(true);
			getScreenDisplayData().getMessage().setScreenResponseMessageColor("color: red;");
			getScreenDisplayData().getMessage().setScreenResponseMessageForUser(MessageConstants.USER_VERIFICATION_FAILED);
			context.addMessage(null, new FacesMessage("Unsuccessful",MessageConstants.USER_VERIFICATION_FAILED));
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	
	
	/**
	 * Verify User Email by comparing verification code entered by user.
	 */
	public void verifyUserEmailActionFromLoginScreen(){
		final String METHOD_NAME = "verifyUserEmailActionFromLoginScreen";
		logger.entering(CLASS_NAME, METHOD_NAME);
		FacesContext context = FacesContext.getCurrentInstance();	
		if(getScreenDisplayData().getVerificationCode().equals(getScreenDisplayData().getRegistedUser().getUserVerificationKey())){
			logger.debug("verification successfull");
			//change status of registered user:start
			UserDetailBO userDetailBO = getScreenDisplayData().getRegistedUser();
			userDetailBO.setUserStatus("VERIFID");

			MaintainUserBD userBD = new MaintainUserBD();
			//update database 
			boolean isSuccessfull = userBD.updateUser(userDetailBO);
			//change status of registered user:end
			if(isSuccessfull){
				logger.debug("user status changed successfully");
				getSessionBean().getScreenDisplayData().setHeaderName(userDetailBO.getFirstName());


				getSessionBean().getRenderer().getDataRenderer().setProductNameRendered(true);
				//End//
			

				getSessionBean().getRenderer().getDataRenderer().setLogoutRenderer(true);// To make Logout link visible in header.
				getSessionBean().getRenderer().getDataRenderer().setUserProfileRendered(true);
				getSessionBean().getScreenDisplayData().setUserEmailId(userDetailBO.getUserEmailId());
				RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/home.faces");
				context.addMessage(null, new FacesMessage("Successful",MessageConstants.USER_VERIFICATION_SUCCESSFULL));
				
			}else{
				logger.debug("some error occured while changing user status");
				getRenderer().getDataRenderer().setSuccessfullDataAddPanelRenderer(true);
				getScreenDisplayData().getMessage().setScreenResponseMessageColor("color: red;");
				getScreenDisplayData().getMessage().setScreenResponseMessageForUser("User Email Verification Failed");
				context.addMessage(null, new FacesMessage("Unsuccessful",MessageConstants.USER_VERIFICATION_FAILED));
			}

		}else{
			logger.debug("verification failed");
			context.addMessage(null, new FacesMessage("Unsuccessful",MessageConstants.USER_VERIFICATION_FAILED));
		}


		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method cancel the registration and redirect user to index page.
	 */
	public void cancelRegistration(){
		final String METHOD_NAME = "cancelRegistration";
		logger.entering(CLASS_NAME, METHOD_NAME);
		this.init();
		RedirectScreen.redirectToPage("/DashBoardPointWeb/index.faces");	
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}	
	
	/**
	 * This method will cancel the email verification process.
	 * Login panel will get randered.
	 */
	public void cancelVerification() {
		final String METHOD_NAME = "cancelVerification";
		logger.entering(CLASS_NAME, METHOD_NAME);		
		getRenderer().getDataRenderer().setVerificationPanel(false);
		RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/authentication/login.faces");
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * This method will cancel the verification process.
	 * Metronic login form will get rendered.
	 */
	public void cancelMetronicVerification() {
		final String METHOD_NAME = "cancelMetronicVerification";
		logger.entering(CLASS_NAME, METHOD_NAME);		
		getRenderer().getDataRenderer().setVerificationPanel(false);
		RedirectScreen.redirectToPage("/DashBoardPointWeb/pages/authentication/metronic_login.faces");
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

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

	/**
	 * This Method Initialize Screen Messages
	 * 
	 * @return void
	 */
	public void initializeAllScreenMessages() {
		final String METHOD_NAME = "initializeAllScreenMessages()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		getScreenDisplayData().setMessage(new ScreenResponseMessageBO());
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	
}
