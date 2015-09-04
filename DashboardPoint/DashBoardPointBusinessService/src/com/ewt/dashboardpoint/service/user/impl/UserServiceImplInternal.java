package com.ewt.dashboardpoint.service.user.impl;

import java.util.List;




import javax.persistence.EntityManager;

import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;

import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.dashboardpoint.service.user.helper.UserObjectPreparationHelper;
import com.ewt.dashboardpoint.service.user.impl.task.CheckIfUserAlreadyRegisteredTask;
import com.ewt.dashboardpoint.service.user.impl.task.CreateVerificationKeyTask;
import com.ewt.dashboardpoint.service.user.impl.task.EncryptPasswordTask;
import com.ewt.dashboardpoint.service.user.impl.task.PersistUserDetailsTask;
import com.ewt.dashboardpoint.service.user.impl.task.FetchAllUserDetailTask;
import com.ewt.dashboardpoint.service.user.impl.task.FetchUserByEmailIdTask;
import com.ewt.dashboardpoint.service.user.impl.task.FetchUserTask;
import com.ewt.dashboardpoint.service.user.impl.task.SendVerificationMailTask;
import com.ewt.dashboardpoint.service.user.impl.task.UpdateUserTask;
import com.ewt.dashboardpoint.service.user.impl.task.UpdateUserToActiveStatusTask;
import com.ewt.framework.logging.ILogger;



/**
 * User Service Impl Internal 
 * @author sankalp
 *
 */
public class UserServiceImplInternal {
	private static String CLASS_NAME = UserServiceImplInternal.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method used to register user in pended state.
	 * @param entityManager
	 * @param userDetailBO
	 * @return
	 */
	public boolean registerUserInPendedState(EntityManager entityManager, UserDetailBO userDetailBO){

		final String METHOD_NAME = "registerUserInPendedState(EntityManager entityManager, UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isUserAlreadyRegistered;
		boolean isProcessSuccess = false;
		//check if user already exist 
		isUserAlreadyRegistered = CheckIfUserAlreadyRegisteredTask.process(entityManager, userDetailBO.getUserEmailId());
		if(isUserAlreadyRegistered){
			return false;
		}else{
			//generate email verification code for user
			String verificationKey = CreateVerificationKeyTask.process();
			userDetailBO.setUserVerificationKey(verificationKey);
			
			//encrypt user password
			String sipherText = EncryptPasswordTask.process(userDetailBO.getPassword());
			userDetailBO.setPassword(sipherText);
			
			//create User Detail BO from screen display data:end
			logger.debug("::::::::::"+userDetailBO.getUserId());
			//convert UserDetailBO object to UserTable object
			UserTable userTable = UserObjectPreparationHelper.convertUserDetailBOToUserEntity(userDetailBO);
			
			//persist user details to database
			PersistUserDetailsTask.process(entityManager, userTable);
			
			//return success
			isProcessSuccess = true;
			logger.debug("user added into the database successfully");
					
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	
	/**
	 * This method send verification mail to user.
	 * @param userDetailBO
	 * @return
	 */
	public boolean sendVerificationMail(UserDetailBO userDetailBO){
		final String METHOD_NAME = "sendVerificationMail(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		//send verification to user email id
		boolean isProcessSuccess = SendVerificationMailTask.process(userDetailBO);	
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}
	
	/**
	 * This method verifies user and change its status to Active state .
	 * @param userDetailBO
	 * @return
	 */
	public boolean verifyUser(EntityManager entityManager,UserDetailBO userDetailBO){
		final String METHOD_NAME = "sendVerificationMail(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isModificationSuccessful= false;
		//getting updated user details from the database
		UserTable userDetailFromDB = FetchUserByEmailIdTask.process(entityManager, userDetailBO.getUserEmailId());
		
		//check if user already verified 
		if(!userDetailFromDB.getUserStatus().equals(ApplicationConstants.USER_ACTIVE_STATUS)){
			logger.debug("user not verified");
			//verify user verification code
			if(userDetailBO.getUserVerificationKey().equals(userDetailFromDB.getUserVerificationKey())){
				logger.debug("User has entered correct verification key");
				//update user details change status to active 
				isModificationSuccessful = UpdateUserToActiveStatusTask.process(entityManager, userDetailFromDB);
				if(isModificationSuccessful){
					logger.debug("user status changed successfuly");
				}else{
					logger.debug("Some error occured while changing user status to Active");
				}
				
			}else{
				logger.debug("verification key does not matches, user verificaiton unsuccessful");
				isModificationSuccessful = false;
			}
		}else{
			logger.debug("user already verified");
			isModificationSuccessful = false;
		}
	
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isModificationSuccessful;
	}
	
	/**
	 * This method creates a user
	 * 
	 * @param EntityManager entityManager
	 * @param userDetailBO userDetailBO
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return boolean isProcessSuccess;
	 */
	
	public boolean createUser(EntityManager entityManager,
			UserDetailBO userDetailBO) {
		final String METHOD_NAME = "createUser(EntityManager entityManager, UserDetailBO userDetailBO, ScreenResponseMessageBO messages)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		boolean isProcessSuccess = false;
		UserTable userTable = UserObjectPreparationHelper.convertUserDetailBOToUserEntity(userDetailBO);
		boolean flag = false;
		flag = CheckIfUserAlreadyRegisteredTask.process(entityManager, userDetailBO.getUserEmailId());
		if(flag == true){
			logger.debug("Duplicate user creation");
		} else{
			PersistUserDetailsTask.process(entityManager, userTable);
			isProcessSuccess = true;
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isProcessSuccess;
	}

	/**
	 * This method fetches user details based on Id
	 * 
	 * @param EntityManager entityManager
	 * @param String userID
	 * 
	 * @return UserTable userEntity;
	 */
	public UserTable fetchUserDetail(EntityManager entityManager,
			int userId) {
		final String METHOD_NAME = "fetchUserDetail(EntityManager entityManager,String userID)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userEntity = FetchUserTask.process(entityManager, userId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userEntity;
		
	}
	
	/**
	 * This method fetches user details based on Email Id
	 * 
	 * @param EntityManager entityManager
	 * @param String userID
	 * 
	 * @return UserTable userEntity;
	 */
	public UserTable fetchUserByEmailId(EntityManager entityManager, String emailAddress) {
		final String METHOD_NAME = "fetchUserByEmailId(EntityManager entityManager,String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userEntity = FetchUserByEmailIdTask.process(entityManager, emailAddress);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userEntity;
		
	}
	
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param EntityManager entityManager

	 * 
	 * @return UserTable userEntity;
	 */
	public List<Object[]> fetchAllUserDetail(EntityManager entityManager)
	{
		final String METHOD_NAME = "fetchAllUserDetail(EntityManager entityManager)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		List<Object[]> userEntity = FetchAllUserDetailTask.process(entityManager);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userEntity;
		
	}
	/**
	 * This method update a user details
	 * 
	 * @param EntityManager entityManager
	 * @param userDetailBO userDetailBO
	 * 
	 * @return true ;
	 */
	public boolean updateUser(EntityManager entityManager,
			UserDetailBO userDetailBO) {
		final String METHOD_NAME = "updateUser(EntityManager entityManager, UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		UserTable userTable = UserObjectPreparationHelper.convertUserDetailBOToUserEntity(userDetailBO);
		
		
		UpdateUserTask.process(entityManager, userTable);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return true;
	}
	
	/**
	 * This method fetches user details based on Email Address
	 * 
	 * @param EntityManager entityManager
	 * @param String emailAddress
	 * 
	 * @return boolean isProcessSuccess;
	 */
	public boolean checkIfEmailAddressExist(EntityManager entityManager,
			String emailAddress) {
		final String METHOD_NAME = "fetchUserByEmailId(EntityManager entityManager, UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isProcessSuccess = CheckIfUserAlreadyRegisteredTask.process(entityManager, emailAddress)	;
		return isProcessSuccess;
		
	}
}
