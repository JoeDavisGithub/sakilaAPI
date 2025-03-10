package com.example.sakila;

import com.example.sakila.controllers.ActorController;
import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
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

import java.util.List;

import static org.mockito.Mockito.*;

public class ActorControllerTest {
    @Mock
    private MockMvc mockMvc;

    final Pageable sourcePageable = mock(Pageable.class);


    static ActorService service = mock(ActorService.class);
    static ActorController controller = new ActorController(service);
    static List<Actor> actors = List.of(
            new Actor((short)1,"FERGUS","BENTLEY","FERGUS BENTLEY", List.of()),
            new Actor((short)2,"ADAD","SAD","ADAD SAD", List.of()),
            new Actor((short)3,"sdsd","asdads","FERGUS BENTLEY", List.of())

    );
    static ActorRequest intervar = new ActorRequest(actors.get(0).getFirstName(),actors.get(0).getLastName(),List.of());
    static ActorRequest intervar2 = new ActorRequest("updatef","updatel",List.of());
    @BeforeAll
    public static void setup() {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(service).getActor(anyShort());
        for (var actor : actors) {
            doReturn(actor).when(service).getActor(actor.getId());
            when(service.createActor(actor.getFirstName(),actor.getLastName(),List.of())).thenReturn(actor);
            //doReturn(actor).when(service).createActor(actor.getFirstName(),actor.getLastName(),List.of());
        }

    }

    @Test
    public void listActorsReturnsActorResponseForAValidActorId() {

        final var expectedResponse = ActorResponse.from(actors.get(0));
        final var actualResponse = controller.listActors((short)1);

        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(),actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(),actualResponse.getLastName());
    }

    @Test
    public void createActorReturnsActorResponseForAValidRequest() {
        final var expectedResponse = ActorResponse.from(actors.get(0));
        final var actualResponse = controller.createActor(intervar);

        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(),actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(),actualResponse.getLastName());
        verify(service).createActor(actors.get(0).getFirstName(),actors.get(0).getLastName(),List.of());

    }


    @Test
    public void alterActorReturnsActorResponseForAValidRequest() {
        final var updatedActor = new Actor((short)1,"updatef","updatel","updatef updatel",List.of());
        final var expectedResponse = ActorResponse.from(updatedActor);
        final Short id=1;
        when(service.updateActor(id,"updatef","updatel",List.of())).thenReturn(updatedActor);
        final var actualResponse = controller.updateActor(intervar2,id);

        Assertions.assertEquals(expectedResponse.getId(),actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(),actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(),actualResponse.getLastName());

    }

    @Test
    public void deleteActorReturnsNullForValidId() {
        final Short id=1;
        controller.deleteActor(id);
        verify(service).deleteActor(id);

    }

    /*@Test
    public void getActorsReturnsPageOfActorResponses() {
        final var expectedResult= new PageImpl(actors.stream().map(ActorResponse::from).toList(),Pageable.ofSize(3),(3));
        doReturn(expectedResult).when(service).getActorPage(PageRequest.of(0,10));
        final var actualResult = controller.getActors(null,null,null);
        //final var actualResult = service.getActorPage(PageRequest.of(1,10));

        Assertions.assertEquals(expectedResult.getTotalPages(),actualResult.getTotalPages());
        Assertions.assertEquals(expectedResult.getContent(),actualResult.getContent());
        Assertions.assertEquals(expectedResult.getPageable(),actualResult.getPageable());
        Assertions.assertEquals(expectedResult.getNumber(),actualResult.getNumber());
        Assertions.assertEquals(expectedResult.getTotalElements(),actualResult.getTotalElements());
        Assertions.assertEquals(expectedResult.getClass(),actualResult.getClass());

    }*/




}
