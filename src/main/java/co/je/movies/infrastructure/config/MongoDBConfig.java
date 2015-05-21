package co.je.movies.infrastructure.config;

import javax.ws.rs.DefaultValue;

public class MongoDBConfig {
	
	private String dbName;

	private String dbPassword;
	
	private String dbUser;
	
	@DefaultValue(value = "localhost")
	private String dbHost;
	
	@DefaultValue(value = "27017")
	private int dbPort;
	

	public String getDbName() {
		return dbName;
	}

	public String getDbPassword() {
		return dbPassword;
	}
	
	public String getDbHost() {
		return dbHost;
	}
	
	public int getDbPort() {
		return dbPort;
	}
	
	public String getDbUser() {
		return dbUser;
	}
	
}
