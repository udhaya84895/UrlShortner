package com.example.urlshortener.models;

import com.fasterxml.jackson.annotation.JsonTypeId;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mappings")
@Getter
@Setter
public class UrlMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "shortUrl", unique = true, nullable = false)
    private String shortUrl;
    @Column(name  = "destinationUrl", unique = true, nullable = false, length = 500)
    private String destinationUrl;
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTimestamp;
    @Column(name = "expiry_timestamp", nullable = false)
    private LocalDateTime expiryTimestamp;

    public UrlMapper() {
        //why we need this?
    }

    public UrlMapper(String shortUrl, String destinationUrl, LocalDateTime createdTimestamp, LocalDateTime expiryTimestamp) {
        this.shortUrl = shortUrl;
        this.destinationUrl = destinationUrl;
        this.createdTimestamp = createdTimestamp;
        this.expiryTimestamp = expiryTimestamp;

    }


}
