package com.example.sakila.controllers;
import com.example.sakila.dto.ValidationGroup;
import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
@CrossOrigin(origins="*")
public class ActorController {

    final ActorService actorService;


    @Autowired
    public ActorController(ActorService actorService){
        this.actorService=actorService;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @GetMapping
    public List<ActorResponse> listActors(@RequestParam(required = false) Optional<String> name) {
        return name
                .map(actorService::findByFullName)
                .orElseGet(actorService::listActors)
                .stream()
                .map(ActorResponse::from)
                .toList();
    }
    /*@GetMapping
    public Page<ActorResponse> getActors(@RequestParam(value = "offset", required = false) Integer offset,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value="sortBy",required = false)String sortBy) {
        if(null == offset) offset = 0;
        if(null == pageSize) pageSize = 10;
        if(null == sortBy) sortBy ="id";
        return actorService.getActorPage(PageRequest.of(offset,pageSize));
    }*/


    @GetMapping("/{id}")
    public ActorResponse listActors(@PathVariable Short id){
        final var actor = actorService.getActor(id);
        return ActorResponse.from(actor);
    }

    @PostMapping
    public ActorResponse createActor(@Validated(ValidationGroup.Create.class) @RequestBody ActorRequest data){
        final var savedactor = actorService.createActor(
                data.getFirstName(),
                data.getLastName(),
                data.getFilmIds()
        );
        return ActorResponse.from(savedactor);
    }

    @DeleteMapping
    public void deleteActor(@RequestParam Short Id){
        actorService.deleteActor(Id);
    }
    //https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html for advanced deletion?

    @PatchMapping("/{Id}")
    public ActorResponse updateActor(@RequestBody ActorRequest data,@PathVariable Short Id) {
        final var savedactor = actorService.updateActor(
                Id,
                data.getFirstName(),
                data.getLastName(),
                data.getFilmIds()
        );
        return ActorResponse.from(savedactor);
    }

}
