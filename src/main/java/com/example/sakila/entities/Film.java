package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="film")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    @Setter(AccessLevel.NONE)
    private Short id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Year release_year;

    @Column(name="language_id")
    private Byte language_id;

    @Column(name="rental_duration")
    private Byte rental_duration;

    @Column(name="rental_rate")
    private Float rental_rate;

    @Column(name="length")
    private Short length;

    @Column(name="replacement_cost")
    private Float replacement_cost;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String special_features="";

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="actor_id")}
    )
    private List<Actor> actors = new ArrayList<Actor>();

    public Year getRelease_year(){
        return release_year;
    }
    public Short getRelease_yearS(){
        return (short)release_year.getValue();
    }

    public List<String> getspecial_features(){
        return Arrays.asList(special_features.split(","));
    }
    public String getspecial_featuresS(){
        return special_features;
    }

    //@ManytoMany(mappedBy="films") <---- works using the established actor version but prevents usage of updating.



}
