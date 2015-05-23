package co.je.movies.persistence.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.je.movies.domain.entities.Movie;
import co.je.movies.persistence.mappers.MovieMapper;

public class MovieDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieDAO.class);

    public void createTableIfNotExists(Connection dbConnection) throws SQLException {

        String createTableIfNeededSQL = "CREATE TABLE IF NOT EXISTS movies "
                + "(imdbId VARCHAR(128) PRIMARY KEY, title VARCHAR(512), runtimeInMinutes SMALLINT, releaseDate TIMESTAMP, filmRating VARCHAR(32), genre VARCHAR(128), "
                + "director VARCHAR(256), plot VARCHAR(1024), metascore SMALLINT, imdbRating DECIMAL(2,1), imdbVotes BIGINT);";

        PreparedStatement prepareStatement = dbConnection.prepareStatement(createTableIfNeededSQL);
        prepareStatement.executeUpdate();
        prepareStatement.close();
    }

    public String createMovie(Connection dbConnection, Movie movie) throws SQLException {

        String createMovieSQL = "INSERT INTO movies (imdbId, title, runtimeInMinutes, releaseDate, filmRating, genre, director, plot, metascore, imdbRating, imdbVotes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement prepareStatement = dbConnection.prepareStatement(createMovieSQL);
        prepareStatement.setString(1, movie.getImdbId());
        prepareStatement.setString(2, movie.getTitle());
        prepareStatement.setInt(3, movie.getRuntimeInMinutes());

        LocalDateTime releaseDate = movie.getReleaseDate();
        prepareStatement.setTimestamp(4, Timestamp.valueOf(releaseDate));

        prepareStatement.setString(5, movie.getFilmRating().getPlainName());
        prepareStatement.setString(6, movie.getGenre());
        prepareStatement.setString(7, movie.getDirector());
        prepareStatement.setString(8, movie.getPlot());
        prepareStatement.setInt(9, movie.getMetascore());
        prepareStatement.setBigDecimal(10, movie.getImdbRating());
        prepareStatement.setLong(11, movie.getImdbVotes());

        LOGGER.info("createMovie: " + prepareStatement);

        prepareStatement.executeUpdate();
        return movie.getImdbId();
    }

    public Optional<Movie> getMovieByImdbId(Connection dbConnection, String imdbId) throws SQLException {

        Optional<Movie> optionalMovie = Optional.empty();
        String getMovieByImdbIdSQL = "SELECT * FROM movies WHERE imdbId = ?;";

        PreparedStatement prepareStatement = dbConnection.prepareStatement(getMovieByImdbIdSQL);
        prepareStatement.setString(1, imdbId);

        LOGGER.info("getMovieByImdbId: " + prepareStatement);

        ResultSet resultSet = prepareStatement.executeQuery();
        optionalMovie = MovieMapper.getSingleMovie(resultSet);

        resultSet.close();
        prepareStatement.close();

        return optionalMovie;
    }

    public List<Movie> getMoviesByParams(Connection dbConnection, String title, int runtimeInMinutes, int metascore,
            BigDecimal imdbRating, long imdbVotes) throws SQLException {

        List<Movie> movies = new ArrayList<Movie>();
        String getMoviesByParamsSQL = "SELECT * FROM movies WHERE title LIKE ? AND runtimeInMinutes >= ? AND metascore >= ? AND imdbRating >= ? AND imdbVotes >= ?;";

        PreparedStatement prepareStatement = dbConnection.prepareStatement(getMoviesByParamsSQL);
        
        String titleQuery = StringUtils.isBlank(title) ? "%" : "%" + title + "%";
        prepareStatement.setString(1, titleQuery);
        
        prepareStatement.setInt(2, runtimeInMinutes);
        prepareStatement.setInt(3, metascore);
        
        BigDecimal imdbRatingQuery = Objects.isNull(imdbRating) ? BigDecimal.ZERO : imdbRating;
        prepareStatement.setBigDecimal(4, imdbRatingQuery);
        
        prepareStatement.setLong(5, imdbVotes);

        LOGGER.info("getMoviesByParams: " + prepareStatement);

        ResultSet resultSet = prepareStatement.executeQuery();
        movies = MovieMapper.getMultipleMovies(resultSet);

        resultSet.close();
        prepareStatement.close();

        return movies;
    }

    public Optional<Movie> updateMovie(Connection dbConnection, String imdbId, Movie movieToUpdate) throws SQLException {
        
        String updateMovieSQL = "UPDATE movies SET title = ?, runtimeInMinutes = ?, releaseDate = ?, filmRating = ?, genre = ?, director = ?, plot = ?, "
                + "metascore = ?, imdbRating = ?, imdbVotes = ? WHERE imdbId = ?;";
        
        PreparedStatement prepareStatement = dbConnection.prepareStatement(updateMovieSQL);
        prepareStatement.setString(1, movieToUpdate.getTitle());
        prepareStatement.setInt(2, movieToUpdate.getRuntimeInMinutes());

        LocalDateTime releaseDate = movieToUpdate.getReleaseDate();
        prepareStatement.setTimestamp(3, Timestamp.valueOf(releaseDate));

        prepareStatement.setString(4, movieToUpdate.getFilmRating().getPlainName());
        prepareStatement.setString(5, movieToUpdate.getGenre());
        prepareStatement.setString(6, movieToUpdate.getDirector());
        prepareStatement.setString(7, movieToUpdate.getPlot());
        prepareStatement.setInt(8, movieToUpdate.getMetascore());
        prepareStatement.setBigDecimal(9, movieToUpdate.getImdbRating());
        prepareStatement.setLong(10, movieToUpdate.getImdbVotes());
        prepareStatement.setString(11, imdbId);
        
        LOGGER.info("updateMovie: " + prepareStatement);
        int rowsAffected = prepareStatement.executeUpdate();
        prepareStatement.close();
        
        Optional<Movie> updatedMovie = (rowsAffected == 1) ? getMovieByImdbId(dbConnection, imdbId) : Optional.empty();
        return updatedMovie;
    }

    public boolean deleteMovie(Connection dbConnection, String imdbId) throws SQLException {
        
        String deleteMovieSQL = "DELETE FROM movies WHERE imdbId = ?;";
        
        PreparedStatement prepareStatement = dbConnection.prepareStatement(deleteMovieSQL);
        prepareStatement.setString(1, imdbId);
        
        LOGGER.info("deleteMovie: " + prepareStatement);
        int rowsAffected = prepareStatement.executeUpdate();
        prepareStatement.close();
        
        boolean movieWasDeleted = (rowsAffected == 1);
        return movieWasDeleted;
    }
}