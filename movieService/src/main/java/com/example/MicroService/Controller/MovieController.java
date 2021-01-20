package com.example.MicroService.Controller;

import com.example.MicroService.Repository.IMovieRepository;
import com.example.MicroService.bo.Movie;
import com.example.MicroService.expection.MovieNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MovieController {

    private final IMovieRepository repository;

    public MovieController(IMovieRepository repository) {
        this.repository = repository;
    }

    //Multiple Item
    @GetMapping("/movies")
    List<Movie> all() throws InterruptedException {
        return repository.findAll();
    }

    @PostMapping("/movies")
    Movie addMovie(@RequestBody Movie newMovie) {
        return repository.save(newMovie);
    }


    //Single Item
    @GetMapping("/movies/{id}")
    Movie getMovieById(@PathVariable Long id, @RequestParam(defaultValue = "0") String delay) throws InterruptedException {
        Thread.sleep(Integer.parseInt(delay));
        return (Movie) repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PutMapping("/movies/{id}")
    Movie replaceMovie(@RequestBody Movie newMovie, @PathVariable Long id) {

        return repository.findById(id)
                .map(movie -> {
                    if (newMovie.getName() != null) {
                        movie.setName(newMovie.getName());
                    }
                    if (newMovie.getDescription() != null) {
                        movie.setDescription(newMovie.getDescription());
                    }
                    return repository.save(movie);
                })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return repository.save(newMovie);
                });
    }

    @DeleteMapping("/movies/{id}")
    void deleteMovieById(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
