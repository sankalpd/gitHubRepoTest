package com.ewt.dashboardpoint.dashboardpointweb.bo.user;

import java.io.Serializable;

import com.ewt.framework.logging.ILogger;

public class UserRenderer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -275029072723647639L;
	private static String CLASS_NAME =  UserRenderer.class.getName();
	private static ILogger logger = new ILogger();
	
	private UserMessageRenderer messageRenderer;
	private UserDataRenderer dataRenderer;
	
	public UserRenderer() {
		logger.entering(CLASS_NAME);
		messageRenderer = new UserMessageRenderer();
		dataRenderer = new UserDataRenderer();
		logger.exiting(CLASS_NAME);
	}
	/**
	 * @return the messageRenderer
	 */
	public UserMessageRenderer getMessageRenderer() {
		return messageRenderer;
	}
	
	/**
	 * @param messageRenderer the messageRenderer to set
	 */
	public void setMessageRenderer(UserMessageRenderer messageRenderer) {
		this.messageRenderer = messageRenderer;
	}

	/**
	 * @return the dataRenderer
	 */
	public UserDataRenderer getDataRenderer() {
		return dataRenderer;
	}

	/**
	 * @param dataRenderer the dataRenderer to set
	 */
	public void setDataRenderer(UserDataRenderer dataRenderer) {
		this.dataRenderer = dataRenderer;
	}
}
