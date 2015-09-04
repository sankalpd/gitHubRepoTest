package com.ewt.dashboardpoint.service.user.impl.task;

import javax.persistence.EntityManager;

import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchUserByEmailIdPersistenceTask;
import com.ewt.framework.logging.ILogger;

public class FetchUserByEmailIdTask {
	private static String CLASS_NAME = FetchUserByEmailIdTask.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * This method returns the UserTable object
	 * 
	 * @param EntityManager entityManager
	 * @param String emailAddress
	 * 
	 * @return UserTable userTable
	 */
	public static UserTable process(EntityManager entityManager,String emailAddress) {
		final String METHOD_NAME = "process(EntityManager entityManager,String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userTable = FetchUserByEmailIdPersistenceTask.process(entityManager, emailAddress);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
}