package com.example.apiSpotify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class Followers {

    @Autowired
    public Followers follower;

    @JsonProperty("total")
    private int total;

    public Followers getFollower() {
        return follower;
    }

    public void setFollower(Followers follower) {
        this.follower = follower;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total.byteValue();
    }


}
