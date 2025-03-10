package com.example.sakila;

import com.example.sakila.controllers.ActorController;
import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.services.ActorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ActorServiceTest {

    @Mock
    private MockMvc mockMvc;


    static ActorRepository actorRepository = mock(ActorRepository.class);
    static FilmRepository filmRepository = mock(FilmRepository.class);
    static ActorService service = new ActorService(actorRepository,filmRepository);

    static List<Film> films = List.of(
            new Film((short)1,"Test","TestingMovie", Year.of(1999), (byte) 1,
                    (byte) 3,(float)4.99,(short)120,(float)20.99,"PG-13",
                    "Behind The Scenes",List.of())
    );


    static List<Actor> actors = List.of(
            new Actor((short)1,"COMP","ARATOR","COMP ARATOR", List.of(films.get(0))),
            new Actor((short)2,"ADAD","SAD","ADAD SAD", List.of()),
            new Actor((short)3,"sdsd","asdads","FERGUS BENTLEY", List.of())

    );

    @BeforeAll
    public static void setup() {
        doReturn(Optional.empty()).when(actorRepository).findById(anyShort());
        doReturn(Optional.empty()).when(filmRepository).findById(anyShort());
        for (var actor : actors) {
            doReturn(Optional.of(actor)).when(actorRepository).findById(actor.getId());

            //doReturn(actor).when(service).createActor(actor.getFirstName(),actor.getLastName(),List.of());
        }
        for (var film : films) {
            doReturn(Optional.of(film)).when(filmRepository).findById(film.getId());

            //doReturn(actor).when(service).createActor(actor.getFirstName(),actor.getLastName(),List.of());
        }


    }

    @Test
    public void getActorReturnsActorForValid() {
        Short id = 1;
        final var expectedResponse = actors.get(0);
        final var actualResponse = service.getActor(id);

        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(),actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(),actualResponse.getLastName());

    }

    @Test
    public void CreateActorReturnsActor() {
        final var expectedResponse = actors.get(0);
        final Actor compActor = new Actor(null,"COMP","ARATOR",null,List.of());
        final Actor compFActor = new Actor((short)1,"COMP","ARATOR","COMP ARATOR",List.of());
        doReturn(compFActor).when(actorRepository).save(compActor);
        final var actualResponse = service.createActor("COMP","ARATOR",List.of());
        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(),actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(),actualResponse.getLastName());

    }

    @Test
    public void UpdateActorReturnsUpdatedActor() {
        final Short id = 2;
        final Actor UpActor = new Actor((short)2,"CHANGED","ACTOR","ADAD SAD",List.of());
        final var expectedResponse = UpActor;

        doReturn(UpActor).when(actorRepository).save(UpActor);
        final var actualResponse = service.updateActor(id,"CHANGED","ACTOR",List.of());

        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(),actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(),actualResponse.getLastName());

    }

    @Test
    public void DeleteActorReturnsNull(){
        final Short id=1;
        service.deleteActor(id);
        verify(actorRepository).deleteById(id);
    }

    /*@Test
    public void getActorPageReturnsPageOfActors(){
        final var expectedResult= new PageImpl(actors.stream().map(ActorResponse::from).toList(), Pageable.ofSize(3),(3));
        doReturn(new PageImpl(actors,Pageable.ofSize(3),(3))).when(actorRepository).findAll(PageRequest.of(0,10));
        final var actualResult = service.getActorPage(PageRequest.of(0,10));

        Assertions.assertEquals(expectedResult.getTotalPages(),actualResult.getTotalPages());
        Assertions.assertEquals(expectedResult.getContent(),actualResult.getContent());
        Assertions.assertEquals(expectedResult.getPageable(),actualResult.getPageable());
        Assertions.assertEquals(expectedResult.getNumber(),actualResult.getNumber());
        Assertions.assertEquals(expectedResult.getTotalElements(),actualResult.getTotalElements());
        Assertions.assertEquals(expectedResult.getClass(),actualResult.getClass());
    }*/


}
