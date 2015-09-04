package com.ewt.dashboardpoint.entity.context;

import javax.persistence.EntityManager;

public class EntityContext {
	
	private String persistenceUnitName;
	private String connectionType;
	private String dbUrl;
	private String driverClass;
	private String schemaName;
	private String datasourceJndiName;
	private String dbUserId;
	private String dbPassword;
	private EntityManager em;
	
	/**
	 * @return the persistenceUnitName
	 */
	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}
	
	/**
	 * @param persistenceUnitName the persistenceUnitName to set
	 */
	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}

	/**
	 * @return the connectionType
	 */
	public String getConnectionType() {
		return connectionType;
	}

	/**
	 * @param connectionType the connectionType to set
	 */
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	/**
	 * @return the dbUrl
	 */
	public String getDbUrl() {
		return dbUrl;
	}

	/**
	 * @param dbUrl the dbUrl to set
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/**
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return driverClass;
	}

	/**
	 * @param driverClass the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/**
	 * @return the schemaName
	 */
	public String getSchemaName() {
		return schemaName;
	}

	/**
	 * @param schemaName the schemaName to set
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	/**
	 * @return the datasourceJndiName
	 */
	public String getDatasourceJndiName() {
		return datasourceJndiName;
	}

	/**
	 * @param datasourceJndiName the datasourceJndiName to set
	 */
	public void setDatasourceJndiName(String datasourceJndiName) {
		this.datasourceJndiName = datasourceJndiName;
	}

	/**
	 * @return the dbUserId
	 */
	public String getDbUserId() {
		return dbUserId;
	}

	/**
	 * @param dbUserId the dbUserId to set
	 */
	public void setDbUserId(String dbUserId) {
		this.dbUserId = dbUserId;
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
