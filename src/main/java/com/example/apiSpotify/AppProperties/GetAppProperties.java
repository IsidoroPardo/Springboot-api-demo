package com.example.apiSpotify.AppProperties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAppProperties {

    private final AppProperties appProperties;

    @Autowired
    public GetAppProperties(AppProperties appProperties){
        this.appProperties = appProperties;
    }

    public String getClientId(){
        return appProperties.getClientId();
    }

    public String getRedirectUri(){
        return appProperties.getRedirectUri();
    }

    @PostConstruct
    public void init() {
        System.out.println("GetAppProperties: ");
        System.out.println("Client ID: " + getClientId());
        System.out.println("Redirect URI: " + getRedirectUri());
        System.out.println();
    }

}
