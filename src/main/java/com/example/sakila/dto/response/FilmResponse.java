package com.example.sakila.dto.response;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class FilmResponse {
    private final Short id;
    private final String title;
    private final String description;
    private final Year release_year;
    private final Short rental_duration;
    private final Float rental_rate;
    private final Short length;
    private final Float replacement_cost;
    private final String rating;
    private final List<String> special_features;
    private final List<PartialActorResponse> actors;

    public static FilmResponse from(Film film){
        return new FilmResponse(
                film.getId(),
                film.getTitle(),
                film.getDescription(),
                film.getRelease_year(),
                film.getRental_duration(),
                film.getRental_rate(),
                film.getLength(),
                film.getReplacement_cost(),
                film.getRating(),
                film.getspecial_features(),
                film.getActors()
                        .stream()
                        .map(PartialActorResponse::from)
                        .toList()
        );

    }
}
