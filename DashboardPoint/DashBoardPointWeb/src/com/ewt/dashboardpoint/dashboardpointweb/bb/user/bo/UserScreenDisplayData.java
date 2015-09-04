package com.ewt.dashboardpoint.dashboardpointweb.bb.user.bo;

import java.io.Serializable;

import java.util.List;

import javax.faces.model.SelectItem;

import com.ewt.dashboardpoint.service.user.bo.UserDetailBO;

public class UserScreenDisplayData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1782651176815990969L;

	private String productName = "Erudi DashBoardPoint";

	// *************** Add User Related Fields*******************************//
	private int userId;
	private String firstName;
	private String lastName;
	private String userEmailId;
	private String userPassword;
	private String reEnteredPassword;
	private String oldPassword;
	private String newPassword;
	private String verificationCode;
	private UserDetailBO registedUser;

	private boolean error;
	private String headerName;
	private String userIdEmailAddress;
	private String passwordEmailAddress;
	private String PassCheckUserId;
	private String changePassword;
	 private String changeConfirmPassword;
	// *************************END*******************************//

	/**
	 * @return the changeConfirmPassword
	 */
	public String getChangeConfirmPassword() {
		return changeConfirmPassword;
	}

	/**
	 * @param changeConfirmPassword the changeConfirmPassword to set
	 */
	public void setChangeConfirmPassword(String changeConfirmPassword) {
		this.changeConfirmPassword = changeConfirmPassword;
	}

	/**
	 * @return the changePassword
	 */
	public String getChangePassword() {
		return changePassword;
	}

	/**
	 * @param changePassword the changePassword to set
	 */
	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	// ***************Screen Message fields*******************************//
	private ScreenResponseMessageBO Message;

	// *************************END*******************************//

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userId
	 */
	public String getUserEmailId() {
		return userEmailId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the reEnteredPassword
	 */
	public String getReEnteredPassword() {
		return reEnteredPassword;
	}

	/**
	 * @param reEnteredPassword
	 *            the reEnteredPassword to set
	 */
	public void setReEnteredPassword(String reEnteredPassword) {
		this.reEnteredPassword = reEnteredPassword;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword
	 *            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the message
	 */
	public ScreenResponseMessageBO getMessage() {
		return Message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(ScreenResponseMessageBO message) {
		Message = message;
	}

	/**
	 * @return the verificationCode
	 */
	public String getVerificationCode() {
		return verificationCode;
	}

	/**
	 * @param verificationCode
	 *            the verificationCode to set
	 */
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	/**
	 * @return the registedUser
	 */
	public UserDetailBO getRegistedUser() {
		return registedUser;
	}

	/**
	 * @param registedUser
	 *            the registedUser to set
	 */
	public void setRegistedUser(UserDetailBO registedUser) {
		this.registedUser = registedUser;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return the headerName
	 */
	public String getHeaderName() {
		return headerName;
	}

	/**
	 * @param headerName
	 *            the headerName to set
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	/**
	 * @return the userIdEmailAddress
	 */
	public String getUserIdEmailAddress() {
		return userIdEmailAddress;
	}

	/**
	 * @param userIdEmailAddress
	 *            the userIdEmailAddress to set
	 */
	public void setUserIdEmailAddress(String userIdEmailAddress) {
		this.userIdEmailAddress = userIdEmailAddress;
	}

	/**
	 * @return the passCheckUserId
	 */
	public String getPassCheckUserId() {
		return PassCheckUserId;
	}

	/**
	 * @param passCheckUserId
	 *            the passCheckUserId to set
	 */
	public void setPassCheckUserId(String passCheckUserId) {
		PassCheckUserId = passCheckUserId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPasswordEmailAddress() {
		return passwordEmailAddress;
	}

	public void setPasswordEmailAddress(String passwordEmailAddress) {
		this.passwordEmailAddress = passwordEmailAddress;
	}

}
