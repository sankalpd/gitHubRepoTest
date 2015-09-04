package com.ewt.dashboardpoint.service.user.impl.task;

import javax.persistence.EntityManager;


import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchUserByEmailIdPersistenceTask;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchUserByIdPersistenceTask;
import com.ewt.framework.logging.ILogger;




public class CheckIfUserExistsTask {
	private static String CLASS_NAME = CheckIfUserExistsTask.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * This method Check whether User data Exists with entered UserId or not
	 * 
	 * @param EntityManager entityManager
	 * @param String userID
	 * 
	 * @return boolean isError;
	 */
	public static boolean process(EntityManager entityManager,String userEmailID) {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);	
		UserTable userTable = FetchUserByEmailIdPersistenceTask.process(entityManager, userEmailID);

		boolean isError = false;
		if(userTable != null){	
			isError = true;
		} 
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return isError;	
	}
}
