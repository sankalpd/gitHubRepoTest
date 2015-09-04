package com.ewt.dashboardpoint.service.user.impl.task;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.UpdateUserPersistenceTask;
import com.ewt.framework.logging.ILogger;



public class UpdateUserTask {
	private static String CLASS_NAME = UpdateUserTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method update  User Details
	 * 
	 * @param EntityManager entityManager
	 * @param UserTable userTable
	 * 
	 * @return true
	 */
	public static boolean process(EntityManager entityManager,UserTable userTable) {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);		
		UpdateUserPersistenceTask.process(entityManager, userTable);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return true;
	}
}

