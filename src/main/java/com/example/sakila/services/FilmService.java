package com.example.sakila.services;


import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.entities.Film;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, ActorRepository actorRepository){
        this.filmRepository=filmRepository;
        this.actorRepository=actorRepository;
    }

    public List<Film> listFilms(){
        return filmRepository.findAll();
    }

    public List<Film> findByTitle(String title){
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }

    public Film getFilm(Short id){
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No actor exists with that ID"));
    }

    public Film createFilm(
            String title, String description, Short release_year,
            Byte language_id, Byte rental_duration,Float rental_rate,
            Short length,Float replacement_cost,String rating,
            String special_features, List<Short> actorIds
    ){
        final var film= new Film();
        film.setTitle(title);
        film.setDescription(description);
        film.setRelease_year(Year.of(release_year));
        film.setLanguage_id(language_id);
        film.setRental_duration(rental_duration);
        film.setRental_rate(rental_rate);
        film.setLength(length);
        film.setReplacement_cost(replacement_cost);
        film.setRating(rating);
        film.setSpecial_features(special_features);

        final var actors = actorIds
                .stream()
                .map(actorId-> actorRepository
                        .findById(actorId)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No Actor with given ID")))
                .toList();
        film.setActors(actors);
        return filmRepository.save(film);
    }

    public void deleteFilm(Short Id){
        filmRepository.deleteById(Id);
    }

    public Film updateFilm(
            Short Id,String title, String description, Short release_year,
            Byte language_id, Byte rental_duration,Float rental_rate,
            Short length,Float replacement_cost,String rating,
            String special_features, List<Short> actorIds
            ) {
        Film film = filmRepository.findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that ID"));
        if (title!=null){
            film.setTitle(title);
        }
        if (description!=null){
            film.setDescription(description);
        }
        if (release_year!=null){
            final Year year = Year.of(release_year);
            film.setRelease_year(year);
        }
        if (language_id!=null){
            film.setLanguage_id(language_id);
        }
        if (rental_duration!=null){
            film.setRental_duration(rental_duration);
        }
        if (rental_rate!=null){
            film.setRental_rate(rental_rate);
        }
        if (length!=null){
            film.setLength(length);
        }
        if (replacement_cost!=null){
            film.setReplacement_cost(replacement_cost);
        }
        if (rating!=null){
            film.setRating(rating);
        }
        if (special_features!=null){
            film.setSpecial_features(special_features);
        }
        if(actorIds!=null){
            final var actors = actorIds
                    .stream()
                    .map(ActorId -> actorRepository
                            .findById(ActorId)
                            .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"No actor exists with that ID")))
                    .toList();
            film.setActors(new ArrayList<>(actors));
        }

        return filmRepository.save(film);
    }



}
