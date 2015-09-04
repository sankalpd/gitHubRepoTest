
package com.ewt.dashboardpoint.service.user.impl.task.persistence;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.persistence.persistence.entity.manager.UserTableManager;
import com.ewt.framework.logging.ILogger;




public class PersistUserPersistenceTask {
	private static String CLASS_NAME = PersistUserPersistenceTask.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * This method creates a user
	 * 
	 * @param EntityManager entityManager
	 * @param UserTable userTable
	 * 
	 * @return void;
	 */
	public static void process(EntityManager entityManager,UserTable userTable) {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		UserTableManager userTableManager = new UserTableManager();
		userTableManager.createUser(entityManager, userTable);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
}
