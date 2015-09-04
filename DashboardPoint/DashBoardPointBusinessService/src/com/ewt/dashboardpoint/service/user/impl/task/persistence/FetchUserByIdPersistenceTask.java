package com.ewt.dashboardpoint.service.user.impl.task.persistence;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.persistence.persistence.entity.manager.UserTableManager;
import com.ewt.framework.logging.ILogger;




public class FetchUserByIdPersistenceTask {
	private static String CLASS_NAME = FetchUserByIdPersistenceTask .class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param EntityManager entityManager
	 * @param String userID
	 * 
	 * @return UserTable userTable
	 */
	public static UserTable process(EntityManager entityManager,int userId) {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTableManager userTableManager = new UserTableManager();
		UserTable userTable =userTableManager.fetchUserDetail(entityManager, userId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
}
