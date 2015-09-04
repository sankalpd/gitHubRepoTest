
package com.ewt.dashboardpoint.service.user.impl;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import com.ewt.dashboardpoint.framework.entity.helper.EntityManagerUtil;
import com.ewt.dashboardpoint.persistence.persistence.entity.UserTable;
import com.ewt.dashboardpoint.service.context.ServiceContext;
import com.ewt.dashboardpoint.service.user.UserService;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;
import com.ewt.dashboardpoint.service.user.helper.UserObjectPreparationHelper;
import com.ewt.framework.logging.ILogger;


public class UserServiceImpl implements UserService {
	private static String CLASS_NAME = UserServiceImpl.class.getName();
	private static ILogger logger = new ILogger();
<<<<<<< HEAD

=======
	
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
	UserServiceImplInternal internalImplInstance = new UserServiceImplInternal();

	/**
	 * This method creates a user
	 * 
	 * @param ServiceContext serviceContext
	 * @param userDetailBO userDetailBO
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return boolean isProcessSuccess;
	 */
	@Override
	public boolean createUser(ServiceContext serviceContext,
			UserDetailBO userDetailBO) {
		final String METHOD_NAME = "createUser(ServiceContext serviceContext,UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);	
		boolean isProcessSuccess = false;	
		EntityManager entityManager = null;	
		try {
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			//Perform Business Logic 
<<<<<<< HEAD

			boolean isEmailIdExists = checkIfEmailAddressExist(serviceContext, userDetailBO.getUserEmailId());

			if (isEmailIdExists) {
				logger.debug("Email Address Already exist's in DataBase");

			} else {

				isProcessSuccess = internalImplInstance.createUser(entityManager,userDetailBO );
				if (isProcessSuccess == false) {
					logger.debug("userId Already exists");

=======
			
			boolean isEmailIdExists = checkIfEmailAddressExist(serviceContext, userDetailBO.getUserEmailId());
			
			if (isEmailIdExists) {
				logger.debug("Email Address Already exist's in DataBase");
			
			} else {
				
				isProcessSuccess = internalImplInstance.createUser(entityManager,userDetailBO );
				if (isProcessSuccess == false) {
					logger.debug("userId Already exists");
				
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
				} else {
					//Commit Transaction
					entityManager.getTransaction().commit();
				}
			}
<<<<<<< HEAD


=======
			
			
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} catch(RollbackException e) {
			logger.debug("RollbackException "+e);
			e.printStackTrace();
		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== ");
			logger.debug("Exception:"+e);
			e.printStackTrace();
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
<<<<<<< HEAD
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
=======
		//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager);
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		// TODO Auto-generated method stub
		return isProcessSuccess;
	}
<<<<<<< HEAD

=======
	
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param ServiceContext serviceContext
	 * @param String userID
	 * 
	 * @return UserDetailBO userBO
	 */
	@Override
	public UserDetailBO fetchUserDetail(ServiceContext serviceContext,
			int userID) {
		final String METHOD_NAME = "fetchUserDetail(ServiceContext serviceContext,UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = null;
<<<<<<< HEAD

=======
				
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		UserTable userTable = new UserTable();
		UserDetailBO userBO = new UserDetailBO();
		try {
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			//Perform Business Logic 
			userTable = internalImplInstance.fetchUserDetail(entityManager,userID);
			userBO = UserObjectPreparationHelper.convertUserEntityToUserDetailBO(userTable);
<<<<<<< HEAD

		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();

=======
			
		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();
			
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
<<<<<<< HEAD
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
=======
		//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager);
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userBO;
	}
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param ServiceContext serviceContext
	 * 
	 * @return UserDetailBO userBO
	 */
	@Override
	public List<UserDetailBO> fetchAllUserDetail(ServiceContext serviceContext) {
		final String METHOD_NAME = "fetchAllUserDetail(ServiceContext serviceContext)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = null;
<<<<<<< HEAD

=======
				
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		List<Object[]> userTableList ;
		List<UserDetailBO> userDetailBOList = null; 
		try {
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			//Perform Business Logic 
			userTableList = internalImplInstance.fetchAllUserDetail(entityManager);
			userDetailBOList = UserObjectPreparationHelper.convertUserDetailEntityListToUserDetailBOList(userTableList);
<<<<<<< HEAD

		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();

=======
			
		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();
			
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
<<<<<<< HEAD
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
=======
		//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager);
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userDetailBOList;
	}
<<<<<<< HEAD

=======
	
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
	/**
	 * This method fetches user details based on Email Id
	 * 
	 * @param ServiceContext serviceContext
	 * @param String userID
	 * 
	 * @return UserDetailBO userBO
	 */
	@Override
	public UserDetailBO fetchUserByEmailId(ServiceContext serviceContext,String emailAddress) {
		final String METHOD_NAME = "fetchUserByEmailId(ServiceContext serviceContext,String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = null;
<<<<<<< HEAD

=======
				
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		UserTable userTable = new UserTable();
		UserDetailBO userBO = new UserDetailBO();
		try {
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			//Perform Business Logic 
			userTable = internalImplInstance.fetchUserByEmailId(entityManager,emailAddress);
			userBO = UserObjectPreparationHelper.convertUserEntityToUserDetailBO(userTable);
<<<<<<< HEAD

		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();

=======
			
		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();
			
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
<<<<<<< HEAD
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
=======
		//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager);
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return userBO;
	}

	/**
	 * This method fetches user details based on Email Address
	 * 
	 * @param ServiceContext serviceContext
	 * @param String emailAddress
	 * 
	 * @return boolean isProcessSuccess
	 */
	@Override
	public boolean checkIfEmailAddressExist(ServiceContext serviceContext, String emailAddress) {
		final String METHOD_NAME = "fetchUserByEmailId(ServiceContext serviceContext,String emailAddress)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = null;
		boolean isProcessSuccess = false;
		try {
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			//Perform Business Logic 
			isProcessSuccess = internalImplInstance.checkIfEmailAddressExist(entityManager, emailAddress);
		} catch(NoResultException e) {
			logger.debug("No Result found with Entered Email Address: "+e);
			e.printStackTrace();
<<<<<<< HEAD

		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== ");
			e.printStackTrace();

=======
			
		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== ");
			e.printStackTrace();
			
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
<<<<<<< HEAD
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
=======
		//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager);
			}
		}
<<<<<<< HEAD
		logger.exiting(CLASS_NAME, METHOD_NAME);
=======
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		return isProcessSuccess;
	}

	/**
	 * This method update  User Details
	 * 
	 * @param ServiceContext serviceContext
	 * @param userDetailBO userDetailBO
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return boolean isProcessSuccess
	 */
	@Override
	public boolean updateUser(ServiceContext serviceContext,
			UserDetailBO userDetailBO) {
		final String METHOD_NAME = "updateUser(ServiceContext serviceContext,UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = null;
<<<<<<< HEAD

=======
	
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		boolean isProcessSuccess = false;
		try {
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			//Perform Business Logic 
			isProcessSuccess = internalImplInstance.updateUser(entityManager, userDetailBO);
			//Commit Transaction
			entityManager.getTransaction().commit();
		}  catch(RollbackException e) {
			logger.debug("RollbackException "+e);
			e.printStackTrace();
			isProcessSuccess = false;
		} catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();
			isProcessSuccess = false;
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
<<<<<<< HEAD
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
=======
		//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager); 
			}
		}
<<<<<<< HEAD
		logger.exiting(CLASS_NAME, METHOD_NAME);
=======
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc
		return isProcessSuccess;
	}

	/**
	 * This method delete a user details
	 * 
	 * @param ServiceContext serviceContext
	 * @param String userID
	 * 
	 * @return true if process is success otherwise it returns false
	 */
	@Override
	public boolean deleteUserDetail(ServiceContext serviceContext, String userID) {
		// TODO Auto-generated method stub
		return false;
	}

<<<<<<< HEAD
	/**
	 * 
	 */
	@Override
	public boolean registerUserInPendedState(ServiceContext serviceContext,
			UserDetailBO userDetailBO) {
		final String METHOD_NAME = "updateUser(ServiceContext serviceContext,UserDetailBO userDetailBO)";
		logger.entering(CLASS_NAME, METHOD_NAME);
		boolean isProcessSuccess=false;
		EntityManager entityManager = null;
		try{
			entityManager = EntityManagerUtil.getEntityManagerUsingDataSrc(serviceContext);
			//Begin Transaction
			entityManager.getTransaction().begin();
			isProcessSuccess = internalImplInstance.registerUserInPendedState(entityManager, userDetailBO);

			if (isProcessSuccess == false) {
				logger.debug("userId Already exists");

			} else {
				//Commit Transaction
				entityManager.getTransaction().commit();
			}
		}catch(Exception e) {
			logger.debug("===========UserServiceImpl EXCEPTION=============== "+e);
			e.printStackTrace();
			isProcessSuccess = false;
			// Release the DB resources
			if (entityManager != null
					&& entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			//Log and Throw ProgrammingException
			//	throw new ProgrammingException(CLASS_NAME, METHOD_NAME, e);
		} finally {
			// Close Entity manager if open
			if (entityManager != null) {
				EntityManagerUtil.closeConnection(entityManager); 
			}
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);

		return isProcessSuccess;
	}

=======
>>>>>>> 5599bb4bb882787631f12081c56f606c8d751dfc


}