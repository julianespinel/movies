package co.je.movies.persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import co.je.movies.domain.entities.Movie;

public class MovieDAO {

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

        prepareStatement.executeUpdate();
        return movie.getImdbId();
    }
}