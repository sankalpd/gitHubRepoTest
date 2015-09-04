package com.ewt.dashboardpoint.dashboardpointweb.bd;

import java.util.List;



import com.ewt.dashboardpoint.service.context.ServiceContext;
import com.ewt.dashboardpoint.service.factory.DashBoardServiceFactory;
import com.ewt.dashboardpoint.service.user.UserService;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.dashboardpoint.service.util.ServiceContextUtil;
import com.ewt.framework.logging.ILogger;



public class MaintainUserBD {
	private static String CLASS_NAME = MaintainUserBD.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This Method Creates User.
	 *  
	 * @param UserDetailBO userDetailBO, 
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return boolean isProcessSuccess;
	 */
	public boolean createUser(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "createUser(UserDetailBO userDetailsBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
		
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		// Create User

		boolean isProcessSuccess = userService.createUser(serviceContext, userDetailBO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	

	/**
	 * This Method Fetches UserDetail.
	 *  
	 * @param String userId, 
	 * 
	 * @return UserDetailBO userDetailBO;
	 */
	public UserDetailBO fetchUserDetail(int userId) {
		final String METHOD_NAME = "featchUserDetail(String userID)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
						
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		//fetch userDetails on the basis of UserId
		UserDetailBO userDetailBO = userService.fetchUserDetail(serviceContext, userId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userDetailBO;
	}
	
	/**
	 * This Method Fetches UserDetail.
	 *  
	 * 
	 * @return UserDetailBO userDetailBO;
	 */
	public List<UserDetailBO> fetchAllUserDetail() {
		final String METHOD_NAME = "featchUserDetail(String userID)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
						
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		//fetch userDetails on the basis of UserId
		List<UserDetailBO> userDetailBOList = userService.fetchAllUserDetail(serviceContext);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userDetailBOList;
	}
	
	/**
	 * This Method Fetches UserDetails by its Email Id .
	 *  
	 * 
	 * @return UserDetailBO userDetailBO;
	 */
	public UserDetailBO fetchUserByEmailId(String emailAddress) {
		final String METHOD_NAME = "fetchUserByEmailId(String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
						
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		//fetch userDetails on the basis of UserId
		UserDetailBO userDetailBO = userService.fetchUserByEmailId(serviceContext,emailAddress);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userDetailBO;
	}
	
	/**
	 * This Method Updates User.
	 *  
	 * @param UserDetailBO userDetailBO, 
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return boolean isProcessSuccess;
	 */
	public boolean updateUser(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "updateUser(UserDetailBO userDetailBO, ScreenResponseMessageBO messages)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
								
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		boolean isProcessSuccess = userService.updateUser(serviceContext,userDetailBO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	
	/**
	 * This Method Deletes User.
	 *  
	 * @param String userId, 
	 * 
	 * @return boolean isProcessSuccess;
	 */
	public boolean deleteUserDetail(String userId) {
		final String METHOD_NAME = "deleteUserDetail(String userID)";
		logger.entering(CLASS_NAME, METHOD_NAME);

		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
										
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
				
		boolean isProcessSuccess = userService.deleteUserDetail(serviceContext, userId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	
	/**
	 * This Method checks whether entered EmailAddress is already in the Database or not.
	 *  
	 * @param String emailAddress, 
	 * 
	 * @return boolean isProcessSuccess;
	 */
	public boolean checkIfEmailAddressExist(String emailAddress) {
		final String METHOD_NAME = "checkIfEmailAddressExist(String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
										
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		boolean isProcessSuccess = userService.checkIfEmailAddressExist(serviceContext, emailAddress);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	

	/**
	 * This method register user in pended state
	 */
	public boolean registerUserInPendedState(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "registerUserInPendedState(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
										
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		boolean isProcessSuccess = userService.registerUserInPendedState(serviceContext, userDetailBO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	
	/**
	 * This method verifies user and change its state to Active
	 */
	public boolean verifyUser(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "verifyUser(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		//Instantiate the service
		UserService userService  = DashBoardServiceFactory.createUserService("db");
										
		// Get service context object
		ServiceContext serviceContext = ServiceContextUtil.buildDashBoardPointServiceContextObject();
		
		boolean isProcessSuccess = userService.verifyUser(serviceContext, userDetailBO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
}
