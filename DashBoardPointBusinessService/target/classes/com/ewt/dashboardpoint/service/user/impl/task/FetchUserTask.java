package com.ewt.dashboardpoint.service.user.impl.task;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchUserByEmailIdPersistenceTask;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchUserByIdPersistenceTask;
import com.ewt.framework.logging.ILogger;



public class FetchUserTask {
	private static String CLASS_NAME = FetchUserTask.class.getName();
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
		UserTable userTable = FetchUserByIdPersistenceTask.process(entityManager, userId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
}

