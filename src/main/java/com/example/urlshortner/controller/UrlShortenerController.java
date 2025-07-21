package com.example.urlshortner.controller;

import com.example.urlshortner.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;

    // Endpoint to create short URL
    @PostMapping("/shorten")
    @ResponseBody
    public ResponseEntity<String> shortenUrl(@RequestParam("url") String url) {
        if(url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }
        String shortUrl = service.shortenUrl(url);
        return ResponseEntity.ok(shortUrl);
    }

    // Redirect short URL to original URL
    @GetMapping("/{shortUrl}")
    public void redirectToOriginal(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = service.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "URL not found");
        }
    }
}
