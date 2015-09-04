package com.ewt.dashboardpoint.service.user.impl.task;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.ewt.framework.logging.ILogger;


public class CreateVerificationKeyTask {
	private static String CLASS_NAME = CreateVerificationKeyTask.class.getName();
	private static ILogger logger = new ILogger();
	
	/**
	 * This method created a random alpha numeric verification key code.
	 * @return UserTable userTable
	 */
	public static String process() {
		final String METHOD_NAME = "process";
		logger.entering(CLASS_NAME, METHOD_NAME);
		SecureRandom random = new SecureRandom();
		String verificationKey =  new BigInteger(130, random).toString(32).substring(0,6).toUpperCase();
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return verificationKey;
	}
}

