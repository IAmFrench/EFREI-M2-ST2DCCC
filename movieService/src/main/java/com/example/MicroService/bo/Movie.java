package com.example.MicroService.bo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Movie  {

    private @Id @GeneratedValue Long id;
    private String name;
    private String description;

    public Movie() {}

    public Movie(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Movie))
            return false;
        Movie movie = (Movie) o;
        return Objects.equals(this.id, movie.id) && Objects.equals(this.name, movie.name)
                && Objects.equals(this.description, movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
