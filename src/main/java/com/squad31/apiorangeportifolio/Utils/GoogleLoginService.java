package com.squad31.apiorangeportifolio.Utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.gson.GsonFactory;

import com.squad31.apiorangeportifolio.Exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleLoginService {

     private final NetHttpTransport transport = new NetHttpTransport();

     private final GsonFactory factory = new GsonFactory();



    public String validate(@Autowired String token, String clientId) {

        GoogleIdToken idToken = null;

        // Create verifier
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList(clientId))
                .build();


        try {
            idToken = verifier.verify(token);
        } catch (GeneralSecurityException | IOException error) {
            throw  new BadRequestException("Invalid Google Token");
        }

        if (idToken == null) {
            throw new BadRequestException("Invalid id_token");
        }
        // Access payload
        return idToken.getPayload().getEmail();
    }








}
