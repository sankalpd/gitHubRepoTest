package com.ewt.dashboardpoint.persistence.persistence.entity.manager;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ewt.framework.logging.ILogger;

public class UserTableManagerTest {

	/**
	 * Package prefixed name of the Class
	 */
	private static String CLASS_NAME = UserTableManagerTest.class.getName();
	
	/**
	 * Logger instance to perform logging
	 */
	private static ILogger logger = new ILogger();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.debug("test setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		logger.debug("test setUp");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateUser() {
		logger.debug("test case");
		assertTrue(true); // TODO
	}

	
}
