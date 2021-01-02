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
            log.info("Preloading " +dataMovies.save(new Movie((long) 1, "Pulpe Fiction", "L'odyssée sanglante et burlesque de petits malfrats dans la jungle de Hollywood à travers trois histoires qui s'entremêlent.")));
            log.info("Preloading " +dataMovies.save(new Movie((long) 2,"Django Unchained", "Un ancien esclave s'associe avec un chasseur de primes d'origine allemande qui l'a libéré : il accepte de traquer avec lui des criminels recherchés")));
            log.info("Preloading " +dataMovies.save(new Movie((long) 3, "Inception", "Dom Cobb est un voleur expérimenté dans l'art périlleux de `l'extraction' : sa spécialité consiste à s'approprier les secrets les plus précieux d'un individu, enfouis au plus profond de son subconscient, pendant qu'il rêve et que son esprit est particulièrement vulnérable.")));
            log.info("Preloading " +dataMovies.save(new Movie((long) 4,"Spiderman", "Peter Parker, alias Spider-Man est un super-héros évoluant dans l'univers Marvel de la maison d'édition Marvel Comics.")));
        };
    }
}
