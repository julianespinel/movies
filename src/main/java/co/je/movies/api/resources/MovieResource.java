package co.je.movies.api.resources;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.je.movies.domain.business.MovieBusiness;
import co.je.movies.domain.entities.Movie;

import com.codahale.metrics.annotation.Timed;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {
    
    private final MovieBusiness movieBusiness;
    
    public MovieResource(MovieBusiness movieBusiness) {
        
        this.movieBusiness = movieBusiness;
    }

    @POST
    @Timed
    public Response createMovie(@Valid Movie movie) {
        
        String imdbId = movieBusiness.createMovie(movie);
        Map<String, String> stringMessage = new HashMap<String, String>();
        stringMessage.put("imdbId", imdbId);
        
        return Response.status(Status.CREATED).entity(stringMessage).build();
    }
    
    @GET
    @Timed
    @Path("/{imdbId}")
    public Response getMovieByImdbId(@PathParam("imdbId") String imdbId) {
        
        Optional<Movie> optionalMovie = movieBusiness.getMovieByImdbId(imdbId);
        Status statusCode = optionalMovie.isPresent() ? Status.OK : Status.NOT_FOUND;
        
        return Response.status(statusCode).entity(optionalMovie).build();
    }
    
    @GET
    @Timed
    public Response getMoviesByParams(@QueryParam("title") String title, @QueryParam("runtimeInMinutes") int runtimeInMinutes,
            @QueryParam("metascore") int metascore, @QueryParam("imdbRating") BigDecimal imdbRating, @QueryParam("imdbVotes") long imdbVotes ) {
        
        List<Movie> movies = movieBusiness.getMoviesByParams(title, runtimeInMinutes, metascore, imdbRating, imdbVotes);
        return Response.status(Status.OK).entity(movies).build();
    }
    
    @PUT
    @Timed
    @Path("/{imdbId}")
    public Response updateMovie(@PathParam("imdbId") String imdbId, @Valid Movie movieToUpdate) {
        
        Optional<Movie> optionalUpdatedMovie = movieBusiness.updateMovie(imdbId, movieToUpdate);
        Status statusCode = optionalUpdatedMovie.isPresent() ? Status.OK : Status.NOT_FOUND;
        
        return Response.status(statusCode).entity(optionalUpdatedMovie).build();
    }
}