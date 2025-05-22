package com.example.apiSpotify.Mappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artists {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
