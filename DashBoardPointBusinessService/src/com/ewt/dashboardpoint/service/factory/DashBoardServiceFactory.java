package com.ewt.dashboardpoint.service.factory;



import com.ewt.dashboardpoint.service.user.UserService;
import com.ewt.dashboardpoint.service.user.impl.UserServiceImpl;
import com.ewt.framework.logging.ILogger;


public class DashBoardServiceFactory {
	
	private static String CLASS_NAME = DashBoardServiceFactory.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method returns Object of UserServiceImpl,if String value
	 *  passes match with the methods String value
	 * 
	 * @param String type
	 * @return new UserServiceImpl() Or null;
	 */
	public static UserService createUserService(String type){
		final String METHOD_NAME = "createUserService(String type)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		if (type.equals("db")) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return new UserServiceImpl();
		} else {
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return null;
		}
	}
}
