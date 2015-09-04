/**
 * 
 */
package com.ewt.dashboardpoint.service.user.impl;

import static org.junit.Assert.*;


import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.dashboardpoint.framework.entity.helper.EntityManagerUtil;
import com.ewt.dashboardpoint.service.context.ServiceContext;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.framework.logging.ILogger;

/**
 * @author sankalp
 *
 */
public class UserServiceImplInternalTest {

	/**
	 * Package prefixed name of the Class
	 */
	private static String CLASS_NAME = UserServiceImplInternalTest.class.getName();
	
	/**
	 * Logger instance to perform logging
	 */
	private static ILogger logger = new ILogger();
	
	/**
	 * _entityManager. Only one instance should be used throughout this test
	 * class
	 */
	protected static EntityManager _entityManager;
	
	/**
	 * _serviceContext. Only one instance should be used throughout this test
	 * class
	 */
	protected static ServiceContext _serviceContext;
	
	/**
	 * _userServiceImplInternal. instance should be used fresh for each test case.
	 */
	protected static UserServiceImplInternal _userServiceImplInternal;
	
	/**
	 * Method executed only once before executing test cases.
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final String METHOD_NAME = "setUpBeforeClass()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.exiting(CLASS_NAME, METHOD_NAME);
			
	}

	/**
	 * Method executed only once after executing test cases.
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		final String METHOD_NAME = "tearDownAfterClass()";
		logger.entering(CLASS_NAME, METHOD_NAME);
;
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 *  Method executed before every test cases.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBeforeTest() throws Exception {
		final String METHOD_NAME = "setUpBeforeTest()";
		logger.entering(CLASS_NAME, METHOD_NAME);
	
		_entityManager = EntityManagerUtil.getEntityManager(ApplicationConstants.PERSISTENCE_UNIT_NAME_TEST);
		_entityManager.getTransaction().begin();
		_userServiceImplInternal = new UserServiceImplInternal();
		
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Method executed after every test cases.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDownAfterTest() throws Exception {
		final String METHOD_NAME = "tearDownAfterTest()";
		logger.entering(CLASS_NAME, METHOD_NAME);
	
		if (_entityManager != null) {
			EntityManagerUtil.closeConnection(_entityManager);

		}
		_entityManager = null;
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Test method for {@link com.ewt.dashboardpoint.service.user.impl.UserServiceImplInternal#createUser(javax.persistence.EntityManager, com.ewt.dashboardpoint.service.user.bo.UserDetailBO)}.
	 * 
	 */
	@Test
	public final void testCreateUserWithValidUserDetails() {
		final String METHOD_NAME = "testCreateUserWithValidUserDetails()";

	
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserDetailBO userDetailBO = new UserDetailBO();
		userDetailBO.setFirstName("TestUserName");
		userDetailBO.setLastName("TestUserLastName");
		userDetailBO.setPassword("testpwd");
		userDetailBO.setUserStatus("VERIFIED");
		userDetailBO.setUserVerificationKey("ABCDEF");
		userDetailBO.setUserEmailId("testUser1@gmail.com");
		
		boolean isValid =_userServiceImplInternal.createUser(_entityManager, userDetailBO);
		
		assertTrue("UserDetail must get persisted successfully", isValid);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Test method for {@link com.ewt.dashboardpoint.service.user.impl.UserServiceImplInternal#createUser(javax.persistence.EntityManager, com.ewt.dashboardpoint.service.user.bo.UserDetailBO)}.
	 */
	@Test
	public final void testCreateUserWithDuplicateEmailID() {
		final String METHOD_NAME = "testCreateUserWithDuplicateEmailID()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		
		UserDetailBO userDetailBO = new UserDetailBO();
		userDetailBO.setFirstName("TestUserName");
		userDetailBO.setLastName("TestUserLastName");
		userDetailBO.setPassword("testpwd");
		userDetailBO.setUserStatus("VERIFIED");
		userDetailBO.setUserVerificationKey("ABCDEF");
		userDetailBO.setUserEmailId("testUser1@gmail.com");
		
		boolean isValid =_userServiceImplInternal.createUser(_entityManager, userDetailBO);
		
		assertTrue("UserDetail must get persisted successfully", isValid);
		
		UserDetailBO duplicateUserDetailBO = new UserDetailBO();
		duplicateUserDetailBO.setFirstName("TestUserName");
		duplicateUserDetailBO.setLastName("TestUserLastName");
		duplicateUserDetailBO.setPassword("testpwd");
		duplicateUserDetailBO.setUserStatus("VERIFIED");
		duplicateUserDetailBO.setUserVerificationKey("ABCDEFG"); // invalid verification code with length 7
		duplicateUserDetailBO.setUserEmailId("testUser1@gmail.com");
		
		isValid =_userServiceImplInternal.createUser(_entityManager, duplicateUserDetailBO);
		
		assertFalse("UserDetail must not get persisted as having duplicate Email ID", isValid);
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
}
