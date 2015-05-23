package co.je.movies.domain.business;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.je.movies.domain.entities.Movie;
import co.je.movies.persistence.daos.MovieDAO;
import co.je.movies.util.factories.MovieFactoryForTests;

public class MovieBusinessTest {
    
    private Connection dbConnectionMock;
    private BasicDataSource dataSourceMock;
    private MovieDAO movieDAOMock;
    private MovieBusiness movieBusiness;
    
    @Before
    public void setUp() {
        
        try {
            
            dbConnectionMock = Mockito.mock(Connection.class);
            dataSourceMock = Mockito.mock(BasicDataSource.class);
            Mockito.when(dataSourceMock.getConnection()).thenReturn(dbConnectionMock);
            
            movieDAOMock = Mockito.mock(MovieDAO.class);
            movieBusiness = new MovieBusiness(dataSourceMock, movieDAOMock);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        
        dbConnectionMock = null;
        dataSourceMock = null;
        movieDAOMock = null;
        movieBusiness = null;
    }
    
    @Test
    public void testCreateMovie_OK() {
        
        try {
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String expectedImdbId = matrixMovie.getImdbId();
            Mockito.when(movieDAOMock.createMovie(dbConnectionMock, matrixMovie)).thenReturn(expectedImdbId);
            
            String imdbId = movieBusiness.createMovie(matrixMovie);
            assertNotNull(imdbId);
            assertEquals(expectedImdbId, imdbId);
            
        } catch (SQLException e) {
            
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testCreateMovie_NOK_SQLException() {
        
        try {
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            Mockito.doThrow(SQLException.class).when(movieDAOMock).createMovie(dbConnectionMock, matrixMovie);
            
            movieBusiness.createMovie(matrixMovie);
            fail("Should send an IllegalStateException after catching the SQLException to break the system.");
            
        } catch (SQLException | IllegalStateException e) {
            
            assertNotNull(e);
            assertTrue(e instanceof IllegalStateException);
        }
    }
}