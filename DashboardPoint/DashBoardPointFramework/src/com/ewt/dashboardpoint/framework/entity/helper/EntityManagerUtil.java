package com.ewt.dashboardpoint.framework.entity.helper;

import javax.persistence.EntityManager;



import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ewt.dashboardpoint.entity.context.EntityContext;
import com.ewt.dashboardpoint.framework.application.constants.ApplicationConstants;
import com.ewt.framework.logging.ILogger;



public class EntityManagerUtil {
	
	private static String CLASS_NAME = EntityManagerUtil.class.getName();
	private static ILogger logger = new ILogger();
	private static EntityManagerFactory emf;
	public static final EntityManagerCounts emConnectionCount = new EntityManagerCounts();
	
	public static void printProperties(String persistenceUnitName) {
		logger.info("Persistence Unit Name", persistenceUnitName);
	}

	public static EntityManager getEntityManager(String persistenceUnitName) {
		final String METHOD_NAME = "getEntityManager(String persistenceUnitName)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EntityManager em = null;
		logger.debug("one");
		try {
			logger.debug("two");
			printProperties(persistenceUnitName);
			logger.debug("three");
			synchronized (EntityManagerUtil.class) {
				logger.debug("four");
				if (emf == null) {
					logger.debug("five");
					emf = Persistence.createEntityManagerFactory(persistenceUnitName);
					logger.debug("six");
				}
			}
			logger.debug("seven");
			
			em = emf.createEntityManager();
			logger.debug("eight");
			emConnectionCount.opened++;
			logger.debug("nine");
			emConnectionCount.active++;
			logger.debug("ten");
		}
		catch(Exception e) {
			logger.debug("EXCEPTION in EntityManagerUtil.getEntityManager: " + e);
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return em;
	}

	public static EntityManager getEntityManagerUsingDataSrc(EntityContext serviceContext) {
		final String METHOD_NAME = "getEntityManagerUsingDataSrc(EntityContext serviceContext)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.debug("persistance unit name:::::::::"+serviceContext.getPersistenceUnitName());
		EntityManager em = EntityManagerUtil.getEntityManager(serviceContext.getPersistenceUnitName());
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return em;
	}

	/**
	 * Utility to close connections. Will rollback if transactions still open
	 * 
	 * @param entityManager
	 */
	public static void closeConnection(EntityManager entityManager) {
		final String METHOD_NAME = "closeConnection(EntityManager entityManager)";
		logger.entering(CLASS_NAME, METHOD_NAME);
//er		logger.trace("Entity manager was closed from class"
//er				+ new Throwable().getStackTrace()[1].getClassName()
//er				+ ",method:"
//er				+ new Throwable().getStackTrace()[1].getMethodName());

		try {
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				try {
				entityManager.getTransaction().rollback();}
				catch(Exception e) {
					e.printStackTrace();
				}				
			}
		} finally {
			
			try {
				if ((entityManager != null) && (entityManager.isOpen())) {
					entityManager.close();
				}
				emConnectionCount.closed++;
				emConnectionCount.active--;
				
//er				log.info("================================================================");
//er				log.info(emConnCnt.toString());
//er				log.info("================================================================");

			} catch (Exception e) {
				throw new RuntimeException(e);
				
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	// Primarily used for JUnit Testing
	public static EntityManager getEntityManagerForJUnit(boolean jpaJunitFlag) {
		final String METHOD_NAME = "getEntityManagerForJUnit(boolean jpaJunitFlag)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		String persistenceUnitName = ApplicationConstants.JUNIT_DERBY_PERSISTENCE_UNIT_NAME;
		EntityManager em = EntityManagerUtil.getEntityManager(persistenceUnitName);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return em;
	}
	
	public static class EntityManagerCounts {
		long active;
		long opened;
		long closed;

		public EntityManagerCounts() {
			active = 0;
			opened = 0;
			closed = 0;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "active=" + active + ", opened=" + opened + ", closed="
					+ closed;
		}	
	};	
	
}

