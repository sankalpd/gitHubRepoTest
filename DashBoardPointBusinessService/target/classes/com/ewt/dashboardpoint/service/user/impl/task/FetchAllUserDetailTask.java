package com.ewt.dashboardpoint.service.user.impl.task;

import java.util.List;



import javax.persistence.EntityManager;

import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchAllUserDetailPersistenceTask;
import com.ewt.framework.logging.ILogger;



public class FetchAllUserDetailTask {
	private static String CLASS_NAME = FetchAllUserDetailTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param EntityManager entityManager
	 * @param String userID
	 * 
	 * @return UserTable userTable
	 */
	public static List<Object[]> process(EntityManager entityManager) {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);
		List<Object[]> userTable = FetchAllUserDetailPersistenceTask.process(entityManager);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
}

