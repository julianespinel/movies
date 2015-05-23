package co.je.movies.persistence.daos;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.commons.dbutils.DbUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.je.movies.domain.entities.Movie;
import co.je.movies.util.factories.MovieFactoryForTests;

public class MovieDAOTest {

    private static Connection dbConnection;
    private MovieDAO movieDAO;

    @BeforeClass
    public static void setUpClass() {

        try {

            String url = "jdbc:h2:mem:test";
            dbConnection = DriverManager.getConnection(url);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownClass() {

        try {
            
            DbUtils.close(dbConnection);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    @Before
    public void setUp() {
        
        try {
            
            movieDAO = new MovieDAO();
            movieDAO.createTableIfNotExists(dbConnection);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        
        movieDAO = null;
        
        try {
            
            String dropTableSQL = "DROP TABLE movies";
            PreparedStatement prepareStatement = dbConnection.prepareStatement(dropTableSQL);
            prepareStatement.executeUpdate();
            prepareStatement.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    private int countMovieTableRows(Connection dbConnection) {
        
        int rows = 0;
        String countMovieTableRowsSQL = "SELECT COUNT(*) FROM movies;";
        
        try (PreparedStatement prepareStatement = dbConnection.prepareStatement(countMovieTableRowsSQL)) {
            
            ResultSet resultSet = prepareStatement.executeQuery();
            rows = resultSet.next() ? resultSet.getInt(1) : 0;
            resultSet.close();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        return rows;
    }
    
    @Test
    public void testCreateMovie_OK() {
        
        try {
            
            int rows = countMovieTableRows(dbConnection);
            assertEquals(0, rows);
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String imdbId = movieDAO.createMovie(dbConnection, matrixMovie);
            assertEquals(matrixMovie.getImdbId(), imdbId);
            
            rows = countMovieTableRows(dbConnection);
            assertEquals(1, rows);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            fail("Unexpeted exception");
        }
    }
    
    @Test
    public void testCreateMovie_NOK_DuplicatedPrimaryKey() {
        
        try {
            
            int rows = countMovieTableRows(dbConnection);
            assertEquals(0, rows);
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String imdbId = movieDAO.createMovie(dbConnection, matrixMovie);
            assertEquals(matrixMovie.getImdbId(), imdbId);
            
            rows = countMovieTableRows(dbConnection);
            assertEquals(1, rows);
            
            movieDAO.createMovie(dbConnection, matrixMovie);
            fail("Should threw an SQLException because that movie was already into teh DB.");
            
        } catch (SQLException e) {
            
            assertNotNull(e);
        }
    }
    
    @Test
    public void testGetMovieByImdbId_OK() {
        
        try {
            
            int rows = countMovieTableRows(dbConnection);
            assertEquals(0, rows);
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String imdbId = movieDAO.createMovie(dbConnection, matrixMovie);
            assertEquals(matrixMovie.getImdbId(), imdbId);
            
            rows = countMovieTableRows(dbConnection);
            assertEquals(1, rows);
            
            Optional<Movie> optionalMovieFromDB = movieDAO.getMovieByImdbId(dbConnection, imdbId);
            assertNotNull(optionalMovieFromDB);
            assertEquals(true, optionalMovieFromDB.isPresent());
            
            assertEquals(0, matrixMovie.compareTo(optionalMovieFromDB.get()));
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            fail("Unexpected exception");
        }
    }
    
    @Test
    public void testGetMovieByImdbId_NOK_emptyTable() {
        
        try {
            
            int rows = countMovieTableRows(dbConnection);
            assertEquals(0, rows);
            
            Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
            String imdbId = matrixMovie.getImdbId();
            
            Optional<Movie> optionalMovieFromDB = movieDAO.getMovieByImdbId(dbConnection, imdbId);
            assertNotNull(optionalMovieFromDB);
            assertEquals(false, optionalMovieFromDB.isPresent());
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            fail("Unexpected exception");
        }
    }
}