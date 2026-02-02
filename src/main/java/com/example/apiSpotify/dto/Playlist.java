package com.example.apiSpotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class Playlist {

    @Autowired
    public Playlist playlist;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("public")
    private boolean hiden;

    public boolean isHiden() {
        return hiden;
    }

    public void setHiden(boolean hiden) {
        this.hiden = hiden;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    @JsonProperty("item")
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
