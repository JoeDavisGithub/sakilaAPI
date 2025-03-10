/*
package com.example.sakila;

import com.example.sakila.controllers.ActorController;
import com.example.sakila.controllers.FilmController;
import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.services.ActorService;
import com.example.sakila.services.FilmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;

import static org.mockito.Mockito.*;

public class FilmControllerTest {
    @Mock
    private MockMvc mockMvc;



    static FilmService service = mock(FilmService.class);
    static FilmController controller = new FilmController(service);
    static List<Film> films = List.of(
            new Film((short)1,"Test","TestingMovie",Year.of(1999), (byte) 1,
                    (byte) 3,(float)4.99,(short)120,(float)20.99,"PG-13",
                    "Behind The Scenes",List.of())
            //(short)1,"Test","TestingMovie",(short)1,(short)3,(float)4.99,(short)120,(float)120,"PG-13","Behind The Scenes", List.of()
    );

    @BeforeAll
    public static void setup() {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(service).getFilm(anyShort());
        for (var film : films) {
            doReturn(film).when(service).getFilm(film.getId());
            when(service.createFilm(film.getTitle(),
                    film.getDescription(),
                    film.getRelease_yearS(),
                    film.getLanguage_id(),
                    film.getRental_duration(),
                    film.getRental_rate(),
                    film.getLength(),
                    film.getReplacement_cost(),
                    film.getRating(),
                    film.getspecial_featuresS(),
                    List.of())).thenReturn(film);
            //doReturn(actor).when(service).createActor(actor.getFirstName(),actor.getLastName(),List.of());
        }
    }

    @Test
    public void listFilmReturnsFilmResponseForAValidFilmId() {

        final var expectedResponse = FilmResponse.from(films.get(0));
        final var actualResponse = controller.listFilms((short)1);

        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getTitle(),actualResponse.getTitle());
        Assertions.assertEquals(expectedResponse.getDescription(),actualResponse.getDescription());
        Assertions.assertEquals(expectedResponse.getRelease_year(),actualResponse.getRelease_year());
        Assertions.assertEquals(expectedResponse.getLanguage_id(),actualResponse.getLanguage_id());
        Assertions.assertEquals(expectedResponse.getRental_duration(),actualResponse.getRental_duration());
        Assertions.assertEquals(expectedResponse.getRental_rate(),actualResponse.getRental_rate());
        Assertions.assertEquals(expectedResponse.getLength(),actualResponse.getLength());
        Assertions.assertEquals(expectedResponse.getReplacement_cost(),actualResponse.getReplacement_cost());
        Assertions.assertEquals(expectedResponse.getRating(),actualResponse.getRating());
        Assertions.assertEquals(expectedResponse.getSpecial_features(),actualResponse.getSpecial_features());
    }

    @Test
    public void createActorReturnsActorResponseForAValidRequest() {
        final var expectedResponse = FilmResponse.from(films.get(0));

        final FilmRequest intervar = new FilmRequest("Test","TestingMovie",(short)1999, (byte) 1,
                (byte) 3,(float)4.99,(short)120,(float)20.99,"PG-13",
                "Behind The Scenes",List.of());
        final var actualResponse = controller.createFilm(intervar);


        verify(service).createActor(actors.get(0).getFirstName(),actors.get(0).getLastName(),List.of());

    }






}
*/
