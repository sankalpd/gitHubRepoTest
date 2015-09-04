package com.ewt.dashboardpoint.dashboardpointweb.bb.util;

import javax.faces.context.FacesContext;


public class RedirectScreen {

	/**
	 * Method for redirecting to the page .
	 *  
	 * @param String page
	 * @return void;
	 */
	public static void redirectToPage(String page) {
		try {
			System.out.println("Redirecting to Page: " + page);
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error While redirecting the page " + page);
		}

	}
}
