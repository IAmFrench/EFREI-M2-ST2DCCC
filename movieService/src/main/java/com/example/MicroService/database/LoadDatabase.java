package com.example.MicroService.database;

import com.example.MicroService.Repository.IMovieRepository;
import com.example.MicroService.bo.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(IMovieRepository dataMovies) {

        return args -> {
            log.info("Preloading " +dataMovies.save(new Movie((long) 1, "Pulpe Fiction", "J'sais pas")));
            log.info("Preloading " +dataMovies.save(new Movie((long) 2,"Django Unchained", "J'sais pas")));
            log.info("Preloading " +dataMovies.save(new Movie((long) 3, "Inception", "J'sais pas")));
            log.info("Preloading " +dataMovies.save(new Movie((long) 4,"Spiderman", "J'sais pas")));
        };
    }
}
