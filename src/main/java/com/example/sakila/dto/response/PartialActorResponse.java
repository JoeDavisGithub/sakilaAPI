package com.example.sakila.dto.response;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;


@AllArgsConstructor
@Getter
public class PartialActorResponse {
    private final Short id;
    private final String fullName;

    public static PartialActorResponse from(Actor actor){
        return new PartialActorResponse(
                actor.getId(),
                actor.getFullName()
        );
    }
}
