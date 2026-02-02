package com.example.apiSpotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class User {

    @Autowired
    public User user;

    @JsonProperty("display_name")
    private String displayName;

    // HAY QUE CREAR EL OBJETO FOLLOWERS
    @JsonProperty("followers")
    private Followers followers;

    @JsonProperty("id")
    private String id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                ", displayName='" + displayName + '\'' +
                ", followers=" + followers.getTotal() +
                ", id='" + id + '\'' +
                '}';
    }
}
