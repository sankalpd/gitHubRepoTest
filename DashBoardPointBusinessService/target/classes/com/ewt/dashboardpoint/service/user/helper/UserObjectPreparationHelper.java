package com.ewt.dashboardpoint.service.user.helper;

import java.util.ArrayList;

import java.util.List;

import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.framework.logging.ILogger;


public class UserObjectPreparationHelper {
	private static String CLASS_NAME = UserObjectPreparationHelper.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method Prepare UserEntity For Creation
	 * 
	 * @param UserDetailBO userDetailBO
	 * 
	 * @return UserTable userTable;
	 */
	public static UserTable prepareUserEntityForCreation(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "prepareUserEntityForCreation(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userTable = convertUserDetailBOToUserEntity(userDetailBO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
		
	}
	
	/**
	 * This method Converts UserDetailBO To UserEntity
	 * 
	 * @param UserDetailBO userDetailBO
	 * 
	 * @return UserTable userTableEntity;
	 */
	public static UserTable convertUserDetailBOToUserEntity(UserDetailBO userDetailBO) {
		final String METHOD_NAME = "convertUserDetailBOToUserEntity(UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userTableEntity = new UserTable();
		
		logger.debug("UserID in UOPHE::::::::::::"+userDetailBO.getUserId());
		userTableEntity.setUserId(userDetailBO.getUserId());
		userTableEntity.setUserFirstName(userDetailBO.getFirstName());
		userTableEntity.setUserLastName(userDetailBO.getLastName());
		userTableEntity.setUserEmailId(userDetailBO.getUserEmailId());
		userTableEntity.setUserPassword(userDetailBO.getPassword());
		userTableEntity.setUserStatus(userDetailBO.getUserStatus());
		userTableEntity.setUserVerificationKey(userDetailBO.getUserVerificationKey());
				
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTableEntity;
	}
	
	/**
	 * This method Converts UserEntity To UserDetailBO 
	 * 
	 * @param UserTable userTableEntity
	 * 
	 * @return UserDetailBO userDetailBO;
	 */
	public static UserDetailBO convertUserEntityToUserDetailBO(UserTable userTableEntity) {
		final String METHOD_NAME = "convertUserEntityToUserDetailBO(UserTable userTableEntity)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserDetailBO userDetailBO = new UserDetailBO();
		if (userTableEntity == null) {
			logger.debug("userTableEntity is NULL");
		} else {
			userDetailBO.setUserId(userTableEntity.getUserId());
			logger.debug("convertUserEntityToUserDetailBO getUserId::::::::::"+userTableEntity.getUserId());
			userDetailBO.setFirstName(userTableEntity.getUserFirstName());
			userDetailBO.setLastName(userTableEntity.getUserLastName());
			userDetailBO.setUserEmailId(userTableEntity.getUserEmailId());
			userDetailBO.setPassword(userTableEntity.getUserPassword());
			userDetailBO.setUserStatus(userTableEntity.getUserStatus());
			userDetailBO.setUserVerificationKey(userTableEntity.getUserVerificationKey());
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userDetailBO;
	}
	/**
	 * This method Converts UserEntity To UserDetailBO 
	 * 
	 * @param UserTable userTableEntity
	 * 
	 * @return UserDetailBO userDetailBO;
	 */
	public static List<UserDetailBO> convertUserDetailEntityListToUserDetailBOList(List<Object[]> userTableEntityList) {
		final String METHOD_NAME = "convertUserDetailEntityListToUserDetailBOList(UserTable userTableEntity)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		List<UserDetailBO> userDetailBOList = new ArrayList<UserDetailBO>();

		if (userTableEntityList == null ) {
			logger.debug("userTableEntityList is NULL");
		} else {
			for(Object[] resultElement : userTableEntityList){
				UserDetailBO userDetailBO = new UserDetailBO();
				userDetailBO.setUserId((int)resultElement[0]);
				userDetailBO.setUserEmailId((String)resultElement[1]);
				userDetailBO.setFirstName((String)resultElement[2]);
				userDetailBO.setLastName((String)resultElement[3]);
				userDetailBO.setUserStatus((String)resultElement[4]);
				userDetailBO.setUserVerificationKey((String)resultElement[5]);
				
				userDetailBOList.add(userDetailBO);
			}
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return userDetailBOList;
	}
}
