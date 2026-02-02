package com.example.apiSpotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Items {

    @Autowired
    public Items userPlaylists;

    @JsonProperty("total")
    private int total;

    @JsonProperty("items")
    private List<Playlist> playlists;

    public Items getUserPlaylists() {
        return userPlaylists;
    }

    public void setUserPlaylists(Items userPlaylists) {
        this.userPlaylists = userPlaylists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total.byteValue();
    }

}