package com.example.sakila.controllers;
import com.example.sakila.dto.ValidationGroup;
import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {

    final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService){
        this.filmService=filmService;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @GetMapping
    public List<FilmResponse> listFilms(@RequestParam(required=false) Optional<String> title){
        return title
                .map(filmService::findByTitle)
                .orElseGet(filmService::listFilms)
                .stream()
                .map(FilmResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public FilmResponse listFilms(@PathVariable Short id){
        final var film = filmService.getFilm(id);
        return  FilmResponse.from(film);
    }

    @PostMapping
    public FilmResponse createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmRequest data){
        final var savedfilm=filmService.createFilm(
                data.getTitle(),
                data.getDescription(),
                data.getRelease_year(),
                data.getLanguage_id(),
                data.getRental_duration(),
                data.getRental_rate(),
                data.getLength(),
                data.getReplacement_cost(),
                data.getRating(),
                data.getSpecial_features(),
                data.getActorIDs()
        );
        return FilmResponse.from(savedfilm);
    }

    @DeleteMapping
    public void deleteFilm(@RequestParam Short Id){
        filmService.deleteFilm(Id);
    }

    @PatchMapping("/{Id}")
    public FilmResponse updateFilm(@RequestBody FilmRequest data, @PathVariable Short Id) {
        final var savedfilm = filmService.updateFilm(
                Id,
                data.getTitle(),
                data.getDescription(),
                data.getRelease_year(),
                data.getLanguage_id(),
                data.getRental_duration(),
                data.getRental_rate(),
                data.getLength(),
                data.getReplacement_cost(),
                data.getRating(),
                data.getSpecial_features(),
                data.getActorIDs()
        );
        return FilmResponse.from(savedfilm);
    }

}
