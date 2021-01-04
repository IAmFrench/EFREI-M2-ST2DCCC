package com.efrei.endpoint;

import com.efrei.model.Movie;
import com.efrei.service.ActorService;
import com.efrei.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ActorService actorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Movie>> findAll()   {
        var movies = movieService.getMovies();
        actorService.enrichList(movies);

        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new  ResponseEntity<>(movies, HttpStatus.OK);
        }
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Movie> findOne(@PathVariable int id) throws ParseException {
        var movie = movieService.getMovie(id);
        actorService.enrich(movie);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
    }
}
