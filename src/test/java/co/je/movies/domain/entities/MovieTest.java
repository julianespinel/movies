package co.je.movies.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.dropwizard.testing.FixtureHelpers;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import co.je.movies.util.factories.MovieFactoryForTests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class MovieTest {
    
    private static ObjectMapper objectMapper;
    
    @BeforeClass
    public static void setUpClass() {
        
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    @Test
    public void serializesToJSON() {
        
        try {
            
            Movie movieFromJSON = objectMapper.readValue(FixtureHelpers.fixture("fixtures/movie.json"), Movie.class);
            String jsonMovieFromFile = objectMapper.writeValueAsString(movieFromJSON);
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String jsonMatrixMovie = objectMapper.writeValueAsString(matrixMovie);
            
            assertEquals(jsonMovieFromFile, jsonMatrixMovie);
            
        } catch (IOException e) {
            
            e.printStackTrace();
            fail("Unexpected exception");
        }
    }
    
    @Test
    public void deserializesFromJSON() {
        
        try {
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            Movie movieFromJSON = objectMapper.readValue(FixtureHelpers.fixture("fixtures/movie.json"), Movie.class);
            assertEquals(0, matrixMovie.compareTo(movieFromJSON));
            
        } catch (IOException e) {
            
            e.printStackTrace();
            fail("Unexpected exception");
        }
    }
}

