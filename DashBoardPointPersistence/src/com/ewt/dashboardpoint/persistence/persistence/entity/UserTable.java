package com.ewt.dashboardpoint.persistence.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USER_TABLE database table.
 * 
 */
@Entity
@Table(name="USER_TABLE")
public class UserTable implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="USER_EMAIL_ID")
	private String userEmailId;

	@Column(name="USER_FIRST_NAME")
	private String userFirstName;

	@Column(name="USER_LAST_NAME")
	private String userLastName;


	@Column(name="USER_PASSWORD")
	private String userPassword;


	@Column(name="USER_STATUS")
	private String userStatus;

	@Column(name="USER_VERIFTN_KEY")
	private String userVerificationKey;


    public UserTable() {
    }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}



	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}


	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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