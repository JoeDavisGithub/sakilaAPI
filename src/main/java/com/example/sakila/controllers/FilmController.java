package com.example.sakila.controllers;
import com.example.sakila.dto.ValidationGroup;
import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins="*")
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
    @GetMapping("/page")
    public Page<FilmResponse> getFilms(@RequestParam(value = "offset", required = false) Integer offset,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value="sortBy",required = false)String sortBy) {
        if(null == offset) offset = 0;
        if(null == pageSize) pageSize = 12;
        if(null == sortBy) sortBy ="id";
        return filmService.getFilmPage(PageRequest.of(offset,pageSize));
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
