package com.ewt.dashboardpoint.service.user.impl.task;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.PersistUserPersistenceTask;
import com.ewt.framework.logging.ILogger;



public class PersistUserDetailsTask {
	private static String CLASS_NAME = PersistUserDetailsTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method creates a user
	 * 
	 * @param EntityManager entityManager
	 * @param UserTable userTable
	 * 
	 * @return void;
	 */
	public static void process(EntityManager entityManager,UserTable userTable){
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		PersistUserPersistenceTask.process(entityManager, userTable);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
}
