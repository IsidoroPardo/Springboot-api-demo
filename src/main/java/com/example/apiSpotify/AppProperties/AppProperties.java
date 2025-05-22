package com.example.apiSpotify.AppProperties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spotify")
@Component
public class AppProperties {

    private String clientId;
    private String redirectUri;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClientId(){
        return this.clientId;
    }

    public String getRedirectUri(){
        return this.redirectUri;
    }

    @PostConstruct
    public void init() {
        System.out.println("AppProperties: ");
        System.out.println("Client ID: " + clientId);
        System.out.println("Redirect URI: " + redirectUri);
        System.out.println();
    }

}
