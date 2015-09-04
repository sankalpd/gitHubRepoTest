package com.ewt.dashboardpoint.service.user.impl.task;

import javax.persistence.EntityManager;

import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.user.impl.task.persistence.FetchUserByEmailIdPersistenceTask;
import com.ewt.framework.logging.ILogger;

public class CheckIfEmailIdExistTask {
	private static String CLASS_NAME = CheckIfEmailIdExistTask.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * This method Check whether entered email Address exists or not
	 * 
	 * @param EntityManager entityManager
	 * @param String emailAddress
	 * 
	 * @return boolean isProcessSuccess
	 */
	public static boolean process(EntityManager entityManager,String emailAddress) {
		final String METHOD_NAME = "process(EntityManager entityManager,String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		 UserTable userTable = FetchUserByEmailIdPersistenceTask.process(entityManager, emailAddress);
		 boolean isProcessSuccess;
			if (userTable != null) {
				logger.debug("Data Found with Entered EmailAdress");
				isProcessSuccess = true;
			} else {
				logger.debug("Data not found with Entered Email Address");
				isProcessSuccess = false;
			}
		return isProcessSuccess;
	}
}
