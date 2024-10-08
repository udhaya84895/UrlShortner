package com.example.urlshortener.controller;

import com.example.urlshortener.exceptions.URLNotFoundException;
import com.example.urlshortener.models.UrlMapper;
import com.example.urlshortener.repository.UrlMapperRepository;
import com.example.urlshortener.service.UrlMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlMapperController {

    @Autowired
    private UrlMapperService urlMapperService;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody String destinationUrl) {
        String shortUrl = urlMapperService.shortenUrl(destinationUrl);
        return ResponseEntity.ok().body(shortUrl);
    }
    @GetMapping("/getshortUrl/{shortUrl}")
    public String getDestinationUrl(@PathVariable String shortUrl) {
        String destinationUrl = urlMapperService.getDestinationUrl(shortUrl);
        if(destinationUrl == null || destinationUrl.isEmpty()) {
            throw new URLNotFoundException("The destination url does not exist");
        }
        return destinationUrl;
    }
}
