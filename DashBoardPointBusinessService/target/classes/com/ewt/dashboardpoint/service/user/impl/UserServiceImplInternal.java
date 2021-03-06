package com.ewt.dashboardpoint.service.user.impl;

import java.util.List;



import javax.persistence.EntityManager;

import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;

import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.dashboardpoint.service.user.helper.UserObjectPreparationHelper;
import com.ewt.dashboardpoint.service.user.impl.task.CheckIfEmailIdExistTask;
import com.ewt.dashboardpoint.service.user.impl.task.CheckIfUserExistsTask;
import com.ewt.dashboardpoint.service.user.impl.task.CreateUserTask;
import com.ewt.dashboardpoint.service.user.impl.task.FetchAllUserDetailTask;
import com.ewt.dashboardpoint.service.user.impl.task.FetchUserByEmailIdTask;
import com.ewt.dashboardpoint.service.user.impl.task.FetchUserTask;
import com.ewt.dashboardpoint.service.user.impl.task.UpdateUserTask;
import com.ewt.framework.logging.ILogger;




public class UserServiceImplInternal {
	private static String CLASS_NAME = UserServiceImplInternal.class.getName();
	private static ILogger logger = new ILogger();
	
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
		flag = CheckIfUserExistsTask.process(entityManager, userDetailBO.getUserEmailId());
		if(flag == true){
			logger.debug("Duplicate user creation");
		} else{
			CreateUserTask.process(entityManager, userTable);
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
		boolean isProcessSuccess = CheckIfEmailIdExistTask.process(entityManager, emailAddress)	;
		return isProcessSuccess;
		
	}
}
