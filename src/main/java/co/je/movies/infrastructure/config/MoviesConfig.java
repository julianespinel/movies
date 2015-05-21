package co.je.movies.infrastructure.config;

import io.dropwizard.Configuration;

public class MoviesConfig extends Configuration {
	
	private MongoDBConfig mongoDBConfig;

    public MongoDBConfig getMongoDBConfig() {
        return mongoDBConfig;
    }
}