package com.ewt.dashboardpoint.service.user.impl.task.persistence;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.persistence.persistence.entity.manager.UserTableManager;
import com.ewt.framework.logging.ILogger;




public class UpdateUserPersistenceTask {
	private static String CLASS_NAME = UpdateUserPersistenceTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method update  User Details
	 * 
	 * @param EntityManager entityManager
	 * @param UserTable userTable
	 * 
	 * @return void;
	 */
	public static void process(EntityManager entityManager,UserTable userTable) {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);
		//create a manager
		UserTableManager userTableManager = new UserTableManager();
		//Do Update
		userTableManager.updateUserDetail(entityManager,userTable);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		//return true;
	}
}






