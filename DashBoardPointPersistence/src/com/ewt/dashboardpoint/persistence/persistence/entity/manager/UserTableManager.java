package com.ewt.dashboardpoint.persistence.persistence.entity.manager;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.framework.logging.ILogger;



public class UserTableManager {
	private static String CLASS_NAME =  UserTableManager.class.getName();
	private ILogger logger = new ILogger();
	
	/**
	 * This Method Creates User.
	 *  
	 * @param EntityManager em, 
	 * @param UserTable userTable
	 * 
	 * @return "";
	 */
	public String createUser(EntityManager em, UserTable userTable) {
		final String METHOD_NAME = "createUser(EntityManager em, UserTable userTable)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		em.persist(userTable);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return "";
	}
	
	/**
	 * This Method Fetches UserDetail.
	 *  
	 * @param EntityManager em, 
	 * @param String userId
	 * 
	 * @return UserTable userTable;
	 */
	public UserTable fetchUserDetail(EntityManager em, int userId) {
		final String METHOD_NAME = "fetchUserDetail(EntityManager em, String userId)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userTable = em.find(UserTable.class,userId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;
	}
	
	/**
	 * This Method Fetches UserDetail.
	 *  
	 * @param EntityManager em, 
	 * 
	 * @return UserTable userTable;
	 */
	public List<Object[]> fetchAllUserDetail(EntityManager em) {
		final String METHOD_NAME = "fetchUserDetail(EntityManager em, String userId)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		String queryString = "SELECT t.userId, t.userEmailId, t.userFirstName, t.userLastName, t.userStatus , t.userVerificationKey from UserTable t ";
		logger.debug(queryString);
		Query query = em.createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<Object[]> result1 = query.getResultList();		
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return result1;
	}
	
	/**
	 * This Method Updates UserDetail.
	 *  
	 * @param EntityManager em, 
	 * @param UserTable userTable
	 * 
	 * @return "";
	 */
	public String updateUserDetail(EntityManager em, UserTable userTable) {
		final String METHOD_NAME = "updateUserDetail(EntityManager em, UserTable userTable)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.debug("UserID:::::::::::::::"+userTable.getUserId());
		em.merge(userTable);
		return "";	
	}
	
	/**
	 * This Method Fetches User By EmailId.
	 *  
	 * @param EntityManager em, 
	 * @param String emailAddress
	 * 
	 * @return Object object;
	 */
	public UserTable fetchUserByEmailId(EntityManager em,String userEmailId) {
		final String METHOD_NAME = "fetchUserByEmailId(EntityManager em, String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		UserTable userTable = null;
		Query query = em.createQuery("SELECT t from UserTable t WHERE t.userEmailId = '" +userEmailId + "'");
		@SuppressWarnings("unchecked")
		List<UserTable> result = query.getResultList();		
		if(result!=null && result.size()>0){
			userTable = result.get(0);
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userTable;	
	}
	
}
