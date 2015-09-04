package com.ewt.dashboardpoint.dashboardpointweb.bb.util;

import java.io.IOException;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ewt.framework.logging.ILogger;


@ManagedBean
@SessionScoped
public class UserSessionData {

	private static String CLASS_NAME = UserSessionData.class.getName();
	
	private String menuParams;
	private String screenName;
	private String errorMessage;
	
	private int activeIndex = 0;
	
	private ILogger logger = new ILogger();

	
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getMenuParams() {
		return menuParams;
	}

	public void setMenuParams(String menuParams) {
		this.menuParams = menuParams;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
	//	this.errorMessage = errorMessage;
		this.errorMessage = "Error Message from usersessiondata";
	}

	/**
	 * This method is called from the Menu item click.
	 * @return void;
	 */
	public void populateScreenNameFromMenu() {
		final String METHOD_NAME = "populateScreenNameFromMenu()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		String delimiter = ":::";
		String[] paramArray = menuParams.split(delimiter);
		
		if (paramArray != null && paramArray.length == 2) {
			this.setScreenName(paramArray[0]);
			logger.info("Redirecting to Screen: ", paramArray[1]);	
		//	setActiveIndex (paramArray[1]);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(paramArray[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//End//
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * This method is called from the Menu item click.
	 * @return void;
	 */
	public void populateScreenNameFromAdminTab() {
		final String METHOD_NAME = "populateScreenNameFromAdminTab()";
		logger.entering(CLASS_NAME, METHOD_NAME);
		String delimiter = ":::";
		String[] paramArray = menuParams.split(delimiter);
		if (paramArray != null && paramArray.length == 2) {
			this.setScreenName(paramArray[0]);
			logger.info("Redirecting to Screen: ", paramArray[1]);	
			setActiveIndex (paramArray[1]);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(paramArray[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * This method sets active Index on tab Click
	 * 
	 * @param String webPagePath
	 * 
	 * @return void;
	 */
	public void setActiveIndex (String webPagePath) {
		final String METHOD_NAME = "setActiveIndex(String webPagePath)";
		logger.entering(CLASS_NAME, METHOD_NAME);
 
		logger.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * @return the activeIndex
	 */
	public int getActiveIndex() {
		return activeIndex;
	}

	/**
	 * @param activeIndex the activeIndex to set
	 */
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

}
