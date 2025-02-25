package com.example.sakila.controllers;

import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;


    @Autowired
    public ActorController(ActorRepository actorRepository, FilmRepository filmRepository){
        this.actorRepository=actorRepository;
        this.filmRepository=filmRepository;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @GetMapping("/actors")
    public List<ActorResponse> listActors(@RequestParam(required = false) Optional<String> name){
        return name
                .map(actorRepository::findByFullNameContainingIgnoreCase)
                .orElseGet(actorRepository::findAll)
                .stream()
                .map(ActorResponse::from)
                .toList();
    }

    @GetMapping("/actors/{id}")
    public ActorResponse listActors(@PathVariable Short id){
        return actorRepository.findById(id)
                .map(ActorResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No actor exists with that ID"));
    }

    @PostMapping("/actors")
    public ActorResponse createActor(@RequestBody ActorRequest data){
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        final var films = data.getFilmIds()
                .stream()
                .map(filmId -> filmRepository
                        .findById(filmId)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No film exists with that ID")))
                .toList();
        actor.setFilms(films);
        final var savedActor = actorRepository.save(actor);
        final var newActor = actorRepository.findById(savedActor.getId())
                .orElseThrow(()->new RuntimeException("Expected the created actor to exist"));
        return ActorResponse.from(newActor);
    }

    @DeleteMapping("/actors")
    public void deleteActor(@RequestParam Short Id){
        actorRepository.deleteById(Id);
    }
}
