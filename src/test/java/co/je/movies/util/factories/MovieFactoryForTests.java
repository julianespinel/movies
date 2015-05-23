package co.je.movies.util.factories;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.je.movies.domain.entities.FilmRating;
import co.je.movies.domain.entities.Movie;

public class MovieFactoryForTests {

    public static Movie getMatrixMovie() {

        String imdbId = "tt0133093";
        String title = "The Matrix";
        int runtimeInMinutes = 136;
        LocalDateTime releaseDate = LocalDateTime.of(1999, 03, 31, 00, 00);
        FilmRating filmRating = FilmRating.R;
        String genre = "Action, Sci-Fi";
        String director = "The Wachowski brothers";
        String plot = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.";
        int metascore = 73;
        BigDecimal imdbRating = new BigDecimal("8.7");
        long imdbVotes = 1023621;

        return new Movie(imdbId, title, runtimeInMinutes, releaseDate, filmRating, genre, director, plot, metascore, imdbRating, imdbVotes);
    }
}