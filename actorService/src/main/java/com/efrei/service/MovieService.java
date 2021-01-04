package com.efrei.service;

import com.efrei.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record MovieService(CircuitBreakerFactory circuitBreakerFactory) {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    public List<Movie> getMovies() {
        var restTemplate = new RestTemplate();

        return circuitBreakerFactory.create("getMovies").run(() -> restTemplate.exchange(
                "http://" + System.getenv("movieService") + "/movies", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Movie>>() { }).getBody(),
            t -> {
                LOGGER.error("getMovies call failed", t);
                return defaultMovies();
            }
        );
    }

    public Movie getMovie(int id) {
        var restTemplate = new RestTemplate();

        return circuitBreakerFactory.create("getMovie").run(() -> restTemplate.exchange(
                "http://" + System.getenv("movieService") + "/movies/"+ id, HttpMethod.GET, null,
                new ParameterizedTypeReference<Movie>() { }).getBody(),
            t -> {
                LOGGER.error("getMovie call failed", t);
                return defaultMovies().get(0);
            }
        );
    }
    private static List<Movie> defaultMovies() {
        return List.of(Movie.builder().id(0).name("FallBack Method").description("C'est l'histoire de deux étudiants qui essayèrent d'implémenter un fallBack et apparemment cela a fonctioné ;D").build());
    }

}
