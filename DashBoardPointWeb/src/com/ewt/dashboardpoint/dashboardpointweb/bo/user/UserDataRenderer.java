package com.ewt.dashboardpoint.dashboardpointweb.bo.user;

import java.io.Serializable;

public class UserDataRenderer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1798587923634554765L;
	//***********addUserBean Related Rendering********************//
	
	private boolean userTextRenderer;
	private boolean verificationPanel;
	private boolean verificationHomeScreenPanel;
	private boolean addUserPanelRenderer;
	
	//**********************userPanelRelated rendering**************//
	
	private boolean userPanelRenderer;
	private boolean userProfileRenderer;
	private boolean userUpdatePanelRenderer;
	private boolean userPasswordCheckPanelRenderer;	
	private boolean changeUserTypePanelRenderer;
	private boolean successfullDataAddPanelRenderer;

	//********************LogInPanel related Rendering*****************//
	
	/* Boolean value to make Logout link in header visible / in visible */
	private boolean logoutRenderer = false;

	/* Boolean value to make Login link in header visible / in visible */
	private boolean loginRenderer = false;
	
	
	
	/* Boolean value to make User Profile link in header visible / in visible */
	private boolean userProfileRendered = false;
	
	/* Boolean value to make product link in header visible / in visible */
	private boolean productNameRendered = false;
	/**
	 * @return the userTextRenderer
	 */
	public boolean isUserTextRenderer() {
		return userTextRenderer;
	}

	/**
	 * @param userTextRenderer the userTextRenderer to set
	 */
	public void setUserTextRenderer(boolean userTextRenderer) {
		this.userTextRenderer = userTextRenderer;
	}

	/**
	 * @return the userPanelRenderer
	 */
	public boolean isUserPanelRenderer() {
		return userPanelRenderer;
	}

	/**
	 * @param userPanelRenderer the userPanelRenderer to set
	 */
	public void setUserPanelRenderer(boolean userPanelRenderer) {
		this.userPanelRenderer = userPanelRenderer;
	}

	/**
	 * @return the userProfileRenderer
	 */
	public boolean isUserProfileRenderer() {
		return userProfileRenderer;
	}

	/**
	 * @param userProfileRenderer the userProfileRenderer to set
	 */
	public void setUserProfileRenderer(boolean userProfileRenderer) {
		this.userProfileRenderer = userProfileRenderer;
	}

	/**
	 * @return the userUpdatePanelRenderer
	 */
	public boolean isUserUpdatePanelRenderer() {
		return userUpdatePanelRenderer;
	}

	/**
	 * @param userUpdatePanelRenderer the userUpdatePanelRenderer to set
	 */
	public void setUserUpdatePanelRenderer(boolean userUpdatePanelRenderer) {
		this.userUpdatePanelRenderer = userUpdatePanelRenderer;
	}

	/**
	 * @return the userPasswordCheckPanelRenderer
	 */
	public boolean isUserPasswordCheckPanelRenderer() {
		return userPasswordCheckPanelRenderer;
	}

	/**
	 * @param userPasswordCheckPanelRenderer the userPasswordCheckPanelRenderer to set
	 */
	public void setUserPasswordCheckPanelRenderer(
			boolean userPasswordCheckPanelRenderer) {
		this.userPasswordCheckPanelRenderer = userPasswordCheckPanelRenderer;
	}


	/**
	 * @return the logoutRenderer
	 */
	public boolean isLogoutRenderer() {
		return logoutRenderer;
	}

	/**
	 * @param logoutRenderer the logoutRenderer to set
	 */
	public void setLogoutRenderer(boolean logoutRenderer) {
		this.logoutRenderer = logoutRenderer;
	}

	/**
	 * @return the loginRenderer
	 */
	public boolean isLoginRenderer() {
		return loginRenderer;
	}

	/**
	 * @param loginRenderer the loginRenderer to set
	 */
	public void setLoginRenderer(boolean loginRenderer) {
		this.loginRenderer = loginRenderer;
	}

	
	/**
	 * @return the verificationPanel
	 */
	public boolean isVerificationPanel() {
		return verificationPanel;
	}

	/**
	 * @param verificationPanel the verificationPanel to set
	 */
	public void setVerificationPanel(boolean verificationPanel) {
		this.verificationPanel = verificationPanel;
	}

	/**
	 * @return the addUserPanelRenderer
	 */
	public boolean isAddUserPanelRenderer() {
		return addUserPanelRenderer;
	}

	/**
	 * @param addUserPanelRenderer the addUserPanelRenderer to set
	 */
	public void setAddUserPanelRenderer(boolean addUserPanelRenderer) {
		this.addUserPanelRenderer = addUserPanelRenderer;
	}

	/**
	 * @return the changeUserTypePanelRenderer
	 */
	public boolean isChangeUserTypePanelRenderer() {
		return changeUserTypePanelRenderer;
	}

	/**
	 * @param changeUserTypePanelRenderer the changeUserTypePanelRenderer to set
	 */
	public void setChangeUserTypePanelRenderer(boolean changeUserTypePanelRenderer) {
		this.changeUserTypePanelRenderer = changeUserTypePanelRenderer;
	}


	/**
	 * @return the userProfileRendered
	 */
	public boolean isUserProfileRendered() {
		return userProfileRendered;
	}

	/**
	 * @param userProfileRendered the userProfileRendered to set
	 */
	public void setUserProfileRendered(boolean userProfileRendered) {
		this.userProfileRendered = userProfileRendered;
	}

	/**
	 * @return the productNameRendered
	 */
	public boolean isProductNameRendered() {
		return productNameRendered;
	}

	/**
	 * @param productNameRendered the productNameRendered to set
	 */
	public void setProductNameRendered(boolean productNameRendered) {
		this.productNameRendered = productNameRendered;
	}

	/**
	 * @return the successfullDataAddPanelRenderer
	 */
	public boolean isSuccessfullDataAddPanelRenderer() {
		return successfullDataAddPanelRenderer;
	}

	/**
	 * @param successfullDataAddPanelRenderer the successfullDataAddPanelRenderer to set
	 */
	public void setSuccessfullDataAddPanelRenderer(
			boolean successfullDataAddPanelRenderer) {
		this.successfullDataAddPanelRenderer = successfullDataAddPanelRenderer;
	}

	/**
	 * @return the verificationHomeScreenPanel
	 */
	public boolean isVerificationHomeScreenPanel() {
		return verificationHomeScreenPanel;
	}

	/**
	 * @param verificationHomeScreenPanel the verificationHomeScreenPanel to set
	 */
	public void setVerificationHomeScreenPanel(boolean verificationHomeScreenPanel) {
		this.verificationHomeScreenPanel = verificationHomeScreenPanel;
	}


}
