package co.je.movies;

import io.dropwizard.Application;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.je.movies.infrastructure.config.MoviesConfig;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class Movies extends Application<MoviesConfig> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Movies.class);

    @Override
    public void initialize(Bootstrap<MoviesConfig> bootstrap) {

        bootstrap.addBundle(new Java8Bundle());
    }

    private void addCORSSupport(Environment environment) {

        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS,PATCH");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }

    private ObjectMapper configureJackson(Environment environment) {

        ObjectMapper objectMapper = environment.getObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    private DB getMongoDB(MoviesConfig moviesConfig) throws UnknownHostException {

    	ServerAddress mongoServer = new ServerAddress(moviesConfig.getMongoDBConfig().getDbHost(), moviesConfig.getMongoDBConfig().getDbPort());
    	MongoClient mongoClient = new MongoClient(mongoServer);
        DB mongoDB = mongoClient.getDB(moviesConfig.getMongoDBConfig().getDbName());

        return mongoDB;
    }

    @Override
    public void run(MoviesConfig moviesConfig, Environment environment) throws Exception {

        // add CORS support
        addCORSSupport(environment);
        
        // Configure Jackson serialization and deserialization
        ObjectMapper objectMapper = configureJackson(environment);

        // Initialize the DB connection
        DB mongoDB = getMongoDB(moviesConfig);
    }

    public static void main(String[] args) {

        try {

            Movies movies = new Movies();
            movies.run(args);

        } catch (Exception e) {

            LOGGER.error("main", e);
        }
    }
}
