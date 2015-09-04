package com.ewt.dashboardpoint.dashboardpointweb.bb.user.bo;

import java.io.Serializable;

public class ScreenResponseMessageBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8255680593306888154L;
	
	private String screenResponseMessageColor;
	
	private String invalidUserEmailIDMsg;
	private String invalidUserPasswordMsg;
	private String invalidUserConfirmPasswordMsg;
	private String invalidUserFirstNameMsg;
	private String invalidUserLastNameMsg;
	private String ScreenResponseMessageForUser;
	
	/**
	 * @return the screenResponseMessageColor
	 */
	public String getScreenResponseMessageColor() {
		return screenResponseMessageColor;
	}
	/**
	 * @param screenResponseMessageColor the screenResponseMessageColor to set
	 */
	public void setScreenResponseMessageColor(String screenResponseMessageColor) {
		this.screenResponseMessageColor = screenResponseMessageColor;
	}
	/**
	 * @return the invalidUserEmailIDMsg
	 */
	public String getInvalidUserEmailIDMsg() {
		return invalidUserEmailIDMsg;
	}
	/**
	 * @param invalidUserEmailIDMsg the invalidUserEmailIDMsg to set
	 */
	public void setInvalidUserEmailIDMsg(String invalidUserEmailIDMsg) {
		this.invalidUserEmailIDMsg = invalidUserEmailIDMsg;
	}
	/**
	 * @return the invalidUserPasswordMsg
	 */
	public String getInvalidUserPasswordMsg() {
		return invalidUserPasswordMsg;
	}
	/**
	 * @param invalidUserPasswordMsg the invalidUserPasswordMsg to set
	 */
	public void setInvalidUserPasswordMsg(String invalidUserPasswordMsg) {
		this.invalidUserPasswordMsg = invalidUserPasswordMsg;
	}
	/**
	 * @return the invalidUserConfirmPasswordMsg
	 */
	public String getInvalidUserConfirmPasswordMsg() {
		return invalidUserConfirmPasswordMsg;
	}
	/**
	 * @param invalidUserConfirmPasswordMsg the invalidUserConfirmPasswordMsg to set
	 */
	public void setInvalidUserConfirmPasswordMsg(
			String invalidUserConfirmPasswordMsg) {
		this.invalidUserConfirmPasswordMsg = invalidUserConfirmPasswordMsg;
	}
	/**
	 * @return the invalidUserFirstNameMsg
	 */
	public String getInvalidUserFirstNameMsg() {
		return invalidUserFirstNameMsg;
	}
	/**
	 * @param invalidUserFirstNameMsg the invalidUserFirstNameMsg to set
	 */
	public void setInvalidUserFirstNameMsg(String invalidUserFirstNameMsg) {
		this.invalidUserFirstNameMsg = invalidUserFirstNameMsg;
	}
	/**
	 * @return the invalidUserLastNameMsg
	 */
	public String getInvalidUserLastNameMsg() {
		return invalidUserLastNameMsg;
	}
	/**
	 * @param invalidUserLastNameMsg the invalidUserLastNameMsg to set
	 */
	public void setInvalidUserLastNameMsg(String invalidUserLastNameMsg) {
		this.invalidUserLastNameMsg = invalidUserLastNameMsg;
	}
	/**
	 * @return the screenResponseMessageForUser
	 */
	public String getScreenResponseMessageForUser() {
		return ScreenResponseMessageForUser;
	}
	/**
	 * @param screenResponseMessageForUser the screenResponseMessageForUser to set
	 */
	public void setScreenResponseMessageForUser(String screenResponseMessageForUser) {
		ScreenResponseMessageForUser = screenResponseMessageForUser;
	}
	
	
}
