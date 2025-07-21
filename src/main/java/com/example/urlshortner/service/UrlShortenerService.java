package com.example.urlshortner.service;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlShortenerService {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    @Autowired
    private UrlMappingRepository repository;

    private SecureRandom random = new SecureRandom();

    public String shortenUrl(String originalUrl) {
        // Generate a random short URL key
        String shortUrl = generateRandomString();

        // Check if it already exists, regenerate if needed
        while(repository.existsById(shortUrl)) {
            shortUrl = generateRandomString();
        }

        // Save mapping
        repository.save(new UrlMapping(shortUrl, originalUrl));

        return BASE_URL + shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return repository.findById(shortUrl)
                .map(UrlMapping::getOriginalUrl)
                .orElse(null);
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
