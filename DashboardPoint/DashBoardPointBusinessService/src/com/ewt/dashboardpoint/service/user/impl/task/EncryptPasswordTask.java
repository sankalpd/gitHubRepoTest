package com.ewt.dashboardpoint.service.user.impl.task;

import com.ewt.dashboardpoint.framework.password.SecurityUtil;
import com.ewt.framework.logging.ILogger;


public class EncryptPasswordTask {
	private static String CLASS_NAME = EncryptPasswordTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method encrypt the password .
	 * @return UserTable userTable
	 */
	public static String process(String pwd) {
		final String METHOD_NAME = "process(String pwd)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		String sipherText = SecurityUtil.encryptText(pwd);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return sipherText;
	}
}

