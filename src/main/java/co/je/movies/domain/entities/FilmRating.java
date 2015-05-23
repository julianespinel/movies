package co.je.movies.domain.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum FilmRating {
    
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");
    
    private final String plainName;

    private FilmRating(String plainName) {
        this.plainName = plainName;
    }
    
    @JsonCreator
    public static FilmRating createFilmRatingFromPlainName(@JsonProperty("plainName") String plainName) {
        
        FilmRating filmRating = null;
        FilmRating[] filmsRatings = FilmRating.values();
        
        for (int i = 0; i < filmsRatings.length && (filmRating == null); i++) {
            
            FilmRating rating = filmsRatings[i];
            boolean ratingsMatch = (rating.getPlainName().equalsIgnoreCase(plainName));
            filmRating = ratingsMatch ? rating : null;
        }
        
        return filmRating;
    }

    public String getPlainName() {
        return plainName;
    }
    
    @Override
    public String toString() {
        
        return getPlainName();
    }
}
