package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
public class ActorController {

    private ActorRepository actorRepository;

    @Autowired
    public ActorController(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }

    @GetMapping("/actors")
    public List<Actor> listActors(){
        return actorRepository.findAll();
    }

    @GetMapping("/actors/{id}")
    public Actor listActors(@PathVariable Short id){
        return actorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No actor with corresponding ID"));
    }
}
