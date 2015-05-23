package co.je.movies.domain.business;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import co.je.movies.domain.entities.Movie;
import co.je.movies.persistence.daos.MovieDAO;

public class MovieBusiness {
    
    private final BasicDataSource dataSource;
    private final MovieDAO movieDAO;
    
    public MovieBusiness(BasicDataSource dataSource, MovieDAO movieDAO) {
        
        this.dataSource = dataSource;
        this.movieDAO = movieDAO;
    }

    public String createMovie(Movie movie) {
        
        String imdbId = "";
        
        try (Connection dbConnection = dataSource.getConnection()) {
            
            imdbId = movieDAO.createMovie(dbConnection, movie);
            
        } catch (SQLException e) {
            
            throw new IllegalStateException(e);
        }
        
        return imdbId;
    }
}