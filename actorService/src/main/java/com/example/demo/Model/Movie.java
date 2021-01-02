package com.example.demo.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

    private int id;
    private String name;
    private String description;
    private List<String> actorsList = new ArrayList<>();

    public Movie(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Movie() {
    }

    public List<String> getActorsList() {
        return actorsList;
    }

    public void addActorToList(String name) {
        actorsList.add(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActorsList(List<String> actorsList) {
        this.actorsList = actorsList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this);
            return json;
        } catch (Exception e) {
            return "{}";
        }
    }
}
