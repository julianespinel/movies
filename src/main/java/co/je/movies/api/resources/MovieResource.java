package co.je.movies.api.resources;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;

import co.je.movies.domain.business.MovieBusiness;
import co.je.movies.domain.entities.Movie;

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
        Response response = Response.status(Status.CREATED).entity(stringMessage).build();
        
        return response;
    }
}