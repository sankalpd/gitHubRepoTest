package com.ewt.dashboardpoint.service.user.impl.task.persistence;

import java.util.List;



import javax.persistence.EntityManager;

import com.ewt.dashboardpoint.persistence.persistence.entity.manager.UserTableManager;
import com.ewt.framework.logging.ILogger;



public class FetchAllUserDetailPersistenceTask {
	private static String CLASS_NAME = FetchAllUserDetailPersistenceTask .class.getName();
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
		UserTableManager userTableManager = new UserTableManager();
		List<Object[]> userTable =userTableManager.fetchAllUserDetail(entityManager);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
}
