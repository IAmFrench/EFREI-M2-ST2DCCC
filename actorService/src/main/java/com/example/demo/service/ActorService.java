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
                movie.addActorToList("John Travolta (VF : Michel Vigné) : Vincent Vega");
                movie.addActorToList("Samuel L. Jackson (VF : Thierry Desroses) : Jules Winnfield");
                break;
            case 2:
                movie.addActorToList("Jamie Foxx (VF : Jean-Baptiste Anoumon ; VQ : Pierre Auger) : Django Freeman");
                movie.addActorToList("Christoph Waltz (VF : Pierre-François Pistorio ; VQ : Denis Gravereaux) : King Schultz");
                break;
            case 3:
                movie.addActorToList("Leonardo DiCaprio (VF : Damien Ferrette ; VQ : Joël Legendre) : Dominic « Dom » Cobb, l'extracteur");
                movie.addActorToList("Joseph Gordon-Levitt (VF : Alexis Victor ; VQ : Hugolin Chevrette-Landesque) : Arthur, l'organisateur ");
                break;
            case 4:
                movie.addActorToList("Matthew McConaughey (VF : Bruno Choël) : Joseph Cooper");
                movie.addActorToList("Anne Hathaway (VF : Caroline Victoria) : Dr Amelia Brand");
                break;

        }
        return movie;
    }
}

