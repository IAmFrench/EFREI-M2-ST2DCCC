package com.efrei.service;

import com.efrei.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record ActorService(CircuitBreakerFactory circuitBreakerFactory) {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    public void enrichList(List<Movie> movies) {
        for (Movie movie : movies) {
            enrich(movie);
        }
    }

    public void enrich(Movie movie) {
        movie.setActors(new ArrayList<>());
        switch (movie.getId()) {
            case 0 -> movie.getActors().addAll(List.of("Alexandre BRANCOLINI", "Alexandre PARES"));
            case 1 -> movie.getActors().addAll(List.of("John Travolta (VF : Michel Vigné) : Vincent Vega", "Samuel L. Jackson (VF : Thierry Desroses) : Jules Winnfield"));
            case 2 -> movie.getActors().addAll(List.of("Jamie Foxx (VF : Jean-Baptiste Anoumon ; VQ : Pierre Auger) : Django Freeman", "Christoph Waltz (VF : Pierre-François Pistorio ; VQ : Denis Gravereaux) : King Schultz"));
            case 3 -> movie.getActors().addAll(List.of("Leonardo DiCaprio (VF : Damien Ferrette ; VQ : Joël Legendre) : Dominic « Dom » Cobb, l'extracteur", "Joseph Gordon-Levitt (VF : Alexis Victor ; VQ : Hugolin Chevrette-Landesque) : Arthur, l'organisateur"));
            case 4 -> movie.getActors().addAll(List.of("Matthew McConaughey (VF : Bruno Choël) : Joseph Cooper", "Anne Hathaway (VF : Caroline Victoria) : Dr Amelia Brand"));
        }
    }
}
