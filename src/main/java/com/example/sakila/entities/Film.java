package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Year;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name="film")
@Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Short id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Year release_year;

    @Column(name="language_id")
    private Short language_id;

    @Column(name="rental_duration")
    private Short rental_duration;

    @Column(name="rental_rate")
    private Float rental_rate;

    @Column(name="length")
    private Short length;

    @Column(name="replacement_cost")
    private Float replacement_cost;

    @Column(name="rating")
    private String rating;

    /*
    @Column(name="special_features")
    private Set<String> special_features;
*/

}
