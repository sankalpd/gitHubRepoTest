package com.ewt.dashboardpoint.service.user.impl.task.persistence;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.persistence.persistence.entity.manager.UserTableManager;
import com.ewt.framework.logging.ILogger;



public class FetchUserByEmailIdPersistenceTask {
	private static String CLASS_NAME = FetchUserByEmailIdPersistenceTask.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * This method fetches user details based on Email Address
	 * 
	 * @param EntityManager entityManager
	 * @param String emailAddress
	 * 
	 * @return UserTable UserTable
	 */
	public static UserTable process(EntityManager entityManager,String emailAddress) {
		final String METHOD_NAME = "process(EntityManager entityManager,String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTableManager userTableManager = new UserTableManager();
		UserTable userTable = userTableManager.fetchUserByEmailId(entityManager, emailAddress);	
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
}
