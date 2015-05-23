package co.je.movies.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.dropwizard.jersey.validation.ConstraintViolationExceptionMapper;
import io.dropwizard.testing.junit.ResourceTestRule;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import co.je.movies.domain.business.MovieBusiness;
import co.je.movies.domain.entities.Movie;
import co.je.movies.util.factories.MovieFactoryForTests;
import co.je.movies.util.factories.ObjectMapperFactoryForTests;

public class MovieResourceTest {

    private static final MovieBusiness movieBusinessMock = Mockito.mock(MovieBusiness.class);
    private static final MovieResource movieResource = new MovieResource(movieBusinessMock);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(movieResource)
            .addProvider(ConstraintViolationExceptionMapper.class)
            .setMapper(ObjectMapperFactoryForTests.getConfiguredObjectMapper()).build();

    @Test
    public void testCreateMovie_OK() {

        String uri = "/movies";
        Movie matrixMovie = MovieFactoryForTests.getMatrixMovie();
        Response response = resources.client().target(uri).request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).post(Entity.entity(matrixMovie, MediaType.APPLICATION_JSON));

        assertNotNull(response);

        int status = response.getStatus();
        assertEquals(201, status);
    }

    @Test
    public void testCreateMovie_NOK_incompleteMovie() {

        String uri = "/movies";
        Movie matrixMovie = MovieFactoryForTests.getNotValidMovie();
        Response response = resources.client().target(uri).request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).post(Entity.entity(matrixMovie, MediaType.APPLICATION_JSON));

        assertNotNull(response);

        int status = response.getStatus();
        assertEquals(422, status);
    }
}