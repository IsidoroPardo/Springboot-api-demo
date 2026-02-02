package com.example.apiSpotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Artist {
    @JsonProperty("name")
    private String name;

    @JsonProperty("popularity")
    private int popularity;

    @JsonProperty("genres")
    private List<String> genres;

    @JsonProperty("id")
    private String id;

    @JsonProperty("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        int value = popularity.intValue();
        this.popularity = value ;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", href=" + href +
                ", popularity=" + popularity +
                ", genres=" + genres +
                '}';
    }

}
