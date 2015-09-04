package com.ewt.dashboardpoint.service.util;


import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.dashboardpoint.service.context.ServiceContext;
import com.ewt.framework.logging.ILogger;

public class ServiceContextUtil {
	private static String CLASS_NAME = ServiceContextUtil.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method builds ServiceContextObject
	 * 
	 * 
	 * @return ServiceContext serviceContext;
	 */
	public static ServiceContext buildDashBoardPointServiceContextObject() {
		final String METHOD_NAME = "buildDashBoardPointServiceContextObject()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		ServiceContext serviceContext = new ServiceContext();
		String dbSchema = "";
		String persistenceUnitName = "";
		String connectionUrl = "";
		String connectionDriverName = "";
		
		persistenceUnitName = ApplicationConstants.PERSISTENCE_UNIT_NAME;
		connectionUrl = ApplicationConstants.DERBY_CONNECTION_URL;
		connectionDriverName = ApplicationConstants.DERBY_CONNECTION_DRIVER_NAME;
		
		serviceContext.setSchemaName(dbSchema);
		serviceContext.setPersistenceUnitName(persistenceUnitName);
		serviceContext.setDbUrl(connectionUrl);
		serviceContext.setDriverClass(connectionDriverName);
		
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return serviceContext;
	}
	
}

