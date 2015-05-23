package co.je.movies.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.dropwizard.testing.FixtureHelpers;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import co.je.movies.util.factories.MovieFactoryForTests;
import co.je.movies.util.factories.ObjectMapperFactoryForTests;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieTest {
    
    private static ObjectMapper objectMapper;
    
    @BeforeClass
    public static void setUpClass() {
        
        objectMapper = ObjectMapperFactoryForTests.getConfiguredObjectMapper();
    }
    
    @Test
    public void serializesToJSON() {
        
        try {
            
            Movie movieFromJSON = objectMapper.readValue(FixtureHelpers.fixture("fixtures/movie.json"), Movie.class);
            String jsonMovieFromFile = objectMapper.writeValueAsString(movieFromJSON);
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String jsonMatrixMovie = objectMapper.writeValueAsString(matrixMovie);
            
            assertEquals("", jsonMatrixMovie);
            
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

