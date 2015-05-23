package co.je.movies.persistence.mappers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import co.je.movies.domain.entities.FilmRating;
import co.je.movies.domain.entities.Movie;

public class MovieMapper {
    
    public static final String IMDB_ID = "imdbId";
    public static final String TITLE = "title";
    public static final String RUNTIME_IN_MINUTES = "runtimeInMinutes";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String FILM_RATING = "filmRating";
    public static final String GENRE = "genre";
    public static final String DIRECTOR = "director";
    public static final String PLOT = "plot";
    public static final String METASCORE = "metascore";
    public static final String IMDB_RATING = "imdbRating";
    public static final String IMDB_VOTES = "imdbVotes";
    
    private static Optional<Movie> getMovieFromResultSet(ResultSet resultSet) throws SQLException {
        
        Optional<Movie> optionalMovie = Optional.empty();
        
        String imdbId = resultSet.getString(IMDB_ID);
        String title = resultSet.getString(TITLE);
        int runtimeInMinutes = resultSet.getInt(RUNTIME_IN_MINUTES);
        LocalDateTime releaseDate = resultSet.getTimestamp(RELEASE_DATE).toLocalDateTime();
        FilmRating filmRating = FilmRating.createFilmRatingFromPlainName(resultSet.getString(FILM_RATING));
        String genre = resultSet.getString(GENRE);
        String director = resultSet.getString(DIRECTOR);
        String plot = resultSet.getString(PLOT);
        int metascore = resultSet.getInt(METASCORE);
        BigDecimal imdbRating = resultSet.getBigDecimal(IMDB_RATING);
        long imdbVotes = resultSet.getLong(IMDB_VOTES);
        
        Movie movie = new Movie(imdbId, title, runtimeInMinutes, releaseDate, filmRating, genre, director, plot, metascore, imdbRating, imdbVotes);
        optionalMovie = Optional.of(movie);
        
        return optionalMovie;
    }
    
    public static Optional<Movie> getSingleMovie(ResultSet resultSet) throws SQLException {
        
        Optional<Movie> optionalMovie = resultSet.next() ? getMovieFromResultSet(resultSet) : Optional.empty();
        return optionalMovie;
    }
}