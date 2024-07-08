package com.example.urlshortener.repository;

import com.example.urlshortener.models.UrlMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMapperRepository extends JpaRepository<UrlMapper, Long> {


    Optional<UrlMapper> findByShortUrl(String shortUrl);

}
