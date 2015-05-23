package co.je.movies.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Movie {
    
    @NotBlank
    private String imdbId;
    
    @NotBlank
    private String title;
    
    @Min(0)
    private int runtimeInMinutes;
    
    @NotNull
    private LocalDateTime releaseDate;
    
    private FilmRating filmRating;
    
    @NotBlank
    private String genre;
    
    @NotBlank
    private String director;
    
    @NotBlank
    private String plot;
    
    private int metascore;
    private BigDecimal imdbRating;
    private long imdbVotes;
    
    public Movie() {
        
    }

    public Movie(String imdbId, String title, int runtimeInMinutes, LocalDateTime releaseDate, FilmRating filmRating,
            String genre, String director, String plot, int metascore, BigDecimal imdbRating, long imdbVotes) {
        
        this.imdbId = imdbId;
        this.title = title;
        this.runtimeInMinutes = runtimeInMinutes;
        this.releaseDate = releaseDate;
        this.filmRating = filmRating;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public int getRuntimeInMinutes() {
        return runtimeInMinutes;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public FilmRating getFilmRating() {
        return filmRating;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getPlot() {
        return plot;
    }

    public int getMetascore() {
        return metascore;
    }

    public BigDecimal getImdbRating() {
        return imdbRating;
    }

    public long getImdbVotes() {
        return imdbVotes;
    }
}