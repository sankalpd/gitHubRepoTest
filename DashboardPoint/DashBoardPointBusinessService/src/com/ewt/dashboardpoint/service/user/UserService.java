package com.ewt.dashboardpoint.service.user;

import java.util.List;



import com.ewt.dashboardpoint.service.context.ServiceContext;
import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;




public interface UserService {
	
	/**
	 * This method creates a user
	 * 
	 * @param ServiceContext serviceContext
	 * @param userDetailBO userDetailBO
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return true/false;
	 */
	
	public boolean createUser(ServiceContext serviceContext, UserDetailBO userDetailBO);
		
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param ServiceContext serviceContext
	 * @param String userID
	 * 
	 * @return UserDetailBO Object
	 */
	public UserDetailBO fetchUserDetail(ServiceContext serviceContext, int userID);
	
	/**
	 * This method fetches user details based on Id
	 * 
	 * @param ServiceContext serviceContext
	 *
	 * @return UserDetailBO Object
	 */
	public List<UserDetailBO> fetchAllUserDetail(ServiceContext serviceContext);
	
	/**
	 * This method fetches user details based on Email Address
	 * 
	 * @param ServiceContext serviceContext
	 * @param String emailAddress
	 * 
	 * @return UserDetailBO object
	 */
	public UserDetailBO fetchUserByEmailId(ServiceContext serviceContext, String emailAddress);
	
	/**
	 * This method fetches user details based on Email Address
	 * 
	 * @param ServiceContext serviceContext
	 * @param String emailAddress
	 * 
	 * @return true if process is success otherwise it returns false
	 */
	public boolean checkIfEmailAddressExist(ServiceContext serviceContext, String emailAddress);
	
	/**
	 * This method update a user details
	 * 
	 * @param ServiceContext serviceContext
	 * @param userDetailBO userDetailBO
	 * @param ScreenResponseMessageBO messages
	 * 
	 * @return true if process is success otherwise it returns false
	 */
	public boolean updateUser(ServiceContext serviceContext, UserDetailBO userDetailBO);

	
	/**
	 * This method delete a user details
	 * 
	 * @param ServiceContext serviceContext
	 * @param String userID
	 * 
	 * @return true if process is success otherwise it returns false
	 */
	public boolean deleteUserDetail(ServiceContext serviceContext, String userID);
	
	/**
	 * This service method used to register user in pended state
	 * @param serviceContext
	 * @param userDetailBO
	 * @return
	 */
	public boolean registerUserInPendedState(ServiceContext serviceContext,UserDetailBO userDetailBO);
	
	/**
	 * This service method used to verify user and changed its status to Active state.
	 * @param serviceContext
	 * @param userDetailBO
	 * @return
	 */
	public boolean verifyUser(ServiceContext serviceContext,UserDetailBO userDetailBO);
}
