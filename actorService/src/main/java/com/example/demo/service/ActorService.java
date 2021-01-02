package com.example.demo.service;

import com.example.demo.Model.Movie;

import java.io.Serializable;

public class ActorService implements Serializable {

    public ActorService() {

    }

    public Movie bind(Movie movie) {

        switch (movie.getId()){
            case 0:
                movie.addActorToList("Alexandre BRANCOLINI");
                movie.addActorToList("Alexandre PARES");
                break;
            case 1:
                movie.addActorToList("Test");
                break;
            case 2:
                movie.addActorToList("Test");
                break;
            case 3:
                movie.addActorToList("Test");
                break;

        }
        return movie;
    }
}

