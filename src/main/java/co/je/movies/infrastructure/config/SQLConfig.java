package co.je.movies.infrastructure.config;


public class SQLConfig {
	
	private String driverClass;
	private String url;
	private String username;
	private String password;
	private int removeAbandonedTimeoutInSeconds;
	private boolean ableToRemoveAbandonedConnections;
	
    public String getDriverClass() {
        return driverClass;
    }
    public String getUrl() {
        return url;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getRemoveAbandonedTimeoutInSeconds() {
        return removeAbandonedTimeoutInSeconds;
    }
    public boolean isAbleToRemoveAbandonedConnections() {
        return ableToRemoveAbandonedConnections;
    }
}