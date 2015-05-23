package co.je.movies.domain.business;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Movie> getMovieByImdbId(String imdbId) {
        
        Optional<Movie> optionalMovie = Optional.empty();
        
        try (Connection dbConnection = dataSource.getConnection()) {
            
            optionalMovie = movieDAO.getMovieByImdbId(dbConnection, imdbId);
            
        } catch (SQLException e) {
            
            throw new IllegalStateException(e);
        }
        
        return optionalMovie;
    }

    public List<Movie> getMoviesByParams(String title, int runtimeInMinutes, int metascore, BigDecimal imdbRating, long imdbVotes) {
        
        List<Movie> movies = new ArrayList<Movie>();
        
        try (Connection dbConnection = dataSource.getConnection()) {
            
            movies = movieDAO.getMoviesByParams(dbConnection, title, runtimeInMinutes, metascore, imdbRating, imdbVotes);
            
        } catch (SQLException e) {
            
            throw new IllegalStateException(e);
        }
        
        return movies;
    }

    public Optional<Movie> updateMovie(String imdbId, Movie movieToUpdate) {
        
        Optional<Movie> optionalUpdatedMovie = Optional.empty();
        
        try (Connection dbConnection = dataSource.getConnection()) {
            
            optionalUpdatedMovie = movieDAO.updateMovie(dbConnection, imdbId, movieToUpdate);
            
        } catch (SQLException e) {
            
            throw new IllegalStateException(e);
        }
        
        return optionalUpdatedMovie;
    }
}