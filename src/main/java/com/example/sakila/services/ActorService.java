package com.example.sakila.services;


import com.example.sakila.dto.ValidationGroup;
import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository, FilmRepository filmRepository){
        this.actorRepository=actorRepository;
        this.filmRepository=filmRepository;
    }

    public List<Actor> listActors() {
        return actorRepository.findAll();
    }

    public Page<ActorResponse> getActorPage(PageRequest pageRequest){
        return actorRepository.findAll(pageRequest).map(ActorResponse::from);
    }


    public List<Actor> findByFullName(String name) {
        return actorRepository.findByFullNameContainingIgnoreCase(name);
    }

    public Actor getActor(Short id){
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No actor exists with that ID"));
    }

    public Actor createActor(String firstName, String lastName, List<Short> filmIds){
        final var actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        final var films = filmIds
                .stream()
                .map(filmId -> filmRepository
                        .findById(filmId)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No film exists with that ID")))
                .toList();
        actor.setFilms(films);
        return actorRepository.save(actor);
    }
    //Can have to possibly return a 204?
    public void deleteActor(Short Id){
        actorRepository.deleteById(Id);
    }

    public Actor updateActor(Short Id, String firstName, String lastName, List<Short> filmIds) {
        Actor actor = actorRepository.findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that ID"));
        if (firstName!=null){
            actor.setFirstName(firstName);
        }

        if (lastName!=null){
            actor.setLastName(lastName);
        }
        if(filmIds!=null){
            final var films = filmIds
                    .stream()
                    .map(filmId -> filmRepository
                            .findById(filmId)
                            .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No film exists with that ID")))
                    .toList();
            actor.setFilms(new ArrayList<>(films));
        }

        return actorRepository.save(actor);
    }

}
