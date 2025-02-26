package com.example.sakila.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@Getter
public class FilmRequest {
    private final String title;
    private final String description;
    private final Year release_year;
    private final Byte language_id;
    private final Byte rental_duration;
    private final Float rental_rate;
    private final Short length;
    private final Float replacement_cost;
    private final String rating;
    private final String special_features;
    private final List<Short> actorIDs;

}
