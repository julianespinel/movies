package co.je.movies.infrastructure.config;

import io.dropwizard.Configuration;

public class MoviesConfig extends Configuration {
	
	private SQLConfig sqlConfig;

    public SQLConfig getSqlConfig() {
        return sqlConfig;
    }
}