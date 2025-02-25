package com.example.sakila.controllers;

import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {
    private ActorRepository actorRepository;
    private FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository,ActorRepository actorRepository){
        this.filmRepository=filmRepository;
        this.actorRepository=actorRepository;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @GetMapping("/films")
    public List<FilmResponse> listFilms(@RequestParam(required=false) Optional<String> title){
        return title
                .map(filmRepository::findByTitleContainingIgnoreCase)
                .orElseGet(filmRepository::findAll)
                .stream()
                .map(FilmResponse::from)
                .toList();
    }

    @GetMapping("/films/{id}")
    public FilmResponse listFilms(@PathVariable Short id){
        return filmRepository.findById(id)
                .map(FilmResponse::from)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No film exists with that ID"));
    }

    @PostMapping("/films")
    public FilmResponse createFilm(@RequestBody FilmRequest data){
        final var film= new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setRelease_year(data.getRelease_year());
        film.setLanguage_id(data.getLanguage_id());
        film.setRental_duration(data.getRental_duration());
        film.setRental_rate(data.getRental_rate());
        film.setLength(data.getLength());
        film.setReplacement_cost(data.getReplacement_cost());
        film.setRating(data.getRating());
        film.setSpecial_features(data.getSpecial_features());

        final var actors = data.getActorIDs()
                .stream()
                .map(actorId-> actorRepository
                        .findById(actorId)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No Actor with given ID")))
                .toList();
        film.setActors(actors);
        final var savedFilm = filmRepository.save(film);
        final var newFilm = filmRepository.findById(savedFilm.getId())
                .orElseThrow(()->new RuntimeException("Expected created film to exist"));
        return FilmResponse.from(newFilm);
    }

    @DeleteMapping("/films")
    public void deleteActor(@RequestParam Short Id){
        filmRepository.deleteById(Id);
    }

    @PatchMapping("/films/{Id}")
    public FilmResponse updateFilm(@RequestBody FilmRequest data, @PathVariable Short Id) {
        Film film = filmRepository.findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that ID"));
        if (data.getTitle()!=null){
            film.setTitle(data.getTitle());
        }

        if (data.getDescription()!=null){
            film.setRelease_year(data.getRelease_year());
        }
        if (data.getLanguage_id()!=null){
            film.setLanguage_id(data.getLanguage_id());
        }
        if (data.getRental_duration()!=null){
            film.setRental_duration(data.getRental_duration());
        }
        if (data.getRental_rate()!=null){
            film.setRental_rate(data.getRental_rate());
        }
        if (data.getLength()!=null){
            film.setLength(data.getLength());
        }
        if (data.getReplacement_cost()!=null){
            film.setReplacement_cost(data.getReplacement_cost());
        }
        if (data.getRating()!=null){
            film.setRating(data.getRating());
        }
        if (data.getSpecial_features()!=null){
            film.setSpecial_features(data.getSpecial_features());
        }
        if(data.getActorIDs()!=null){
            final var actors = data.getActorIDs()
                    .stream()
                    .map(ActorId -> actorRepository
                            .findById(ActorId)
                            .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No actor exists with that ID")))
                    .toList();
            film.setActors(new ArrayList<>(actors));
        }
        final var savedFilm = filmRepository.save(film);
        final var newFilm = filmRepository.findById(savedFilm.getId())
                .orElseThrow(()->new RuntimeException("Expected the created actor to exist"));
        return FilmResponse.from(newFilm);
    }

}
