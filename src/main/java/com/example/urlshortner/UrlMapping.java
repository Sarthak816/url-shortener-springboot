package com.example.urlshortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UrlMapping {

    @Id
    private String shortUrl; // short code key

    private String originalUrl; // full original URL

    public UrlMapping() {}

    public UrlMapping(String shortUrl, String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    // Getters and setters
    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
