/**
 * 
 */
package com.ewt.dashboardpoint.junit.util;

import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.dashboardpoint.service.context.ServiceContext;
import com.ewt.dashboardpoint.service.util.ServiceContextUtil;
import com.ewt.framework.logging.ILogger;

/**
 * @author sankalp
 *
 */
public class JunitInitUtil {


	private static String CLASS_NAME = ServiceContextUtil.class.getName();
	private static ILogger logger = new ILogger();

	/**
	 * This method builds ServiceContextObject
	 * 
	 * 
	 * @return ServiceContext serviceContext;
	 */
	public static ServiceContext buildDashBoardPointServiceContextObject() {
		final String METHOD_NAME = "buildDashDoardBointServiceContextObject()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		ServiceContext serviceContext = new ServiceContext();
		String dbSchema = "";
		String persistenceUnitName = "";
		String connectionUrl = "";
		String connectionDriverName = "";

		persistenceUnitName = ApplicationConstants.PERSISTENCE_UNIT_NAME_TEST;
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
