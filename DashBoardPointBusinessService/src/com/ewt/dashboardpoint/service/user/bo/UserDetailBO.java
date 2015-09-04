package com.ewt.dashboardpoint.service.user.bo;

import java.io.Serializable;

public class UserDetailBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 486861163086820924L;
	
	
	private int userId;
	private String userEmailId;
	private String password;
	private String firstName;
	private String lastName;
	private String userStatus;
	private String userVerificationKey;



	
	
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userEmailId
	 */
	public String getUserEmailId() {
		return userEmailId;
	}

	/**
	 * @param userEmailId the userEmailId to set
	 */
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		if (password == null) {
			return "";
		}
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		if (firstName == null) {
			return "";
		}
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}





	/**
	 * @return the lastName
	 */
	public String getLastName() {
		if (lastName == null) {
			return "";
		}
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserVerificationKey() {
		return userVerificationKey;
	}

	public void setUserVerificationKey(String userVerificationKey) {
		this.userVerificationKey = userVerificationKey;
	}



}
