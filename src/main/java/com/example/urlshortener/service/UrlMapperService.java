package com.example.urlshortener.service;

import com.example.urlshortener.models.UrlMapper;
import com.example.urlshortener.repository.UrlMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Component
@Service
public class UrlMapperService {

    private final String BASE62_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private UrlMapperRepository urlMapperRepository;

    public String shortenUrl(String destinationUrl){
        String shortUrl = generateUrl();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusDays(7);
        UrlMapper urlMapper = new UrlMapper(shortUrl,destinationUrl,now,expiryDate);
        urlMapperRepository.save(urlMapper);

        return "http://localhost:8080/api/getshortUrl/"+  shortUrl;
    }
    public String generateUrl(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10;i++){
            int index = (int) (Math.random()*BASE62_CHAR.length());
            sb.append(BASE62_CHAR.charAt(index));
        }

        return sb.toString();
    }

    public String getDestinationUrl(String shortUrl) {
        Optional<UrlMapper> destinationUrl = urlMapperRepository.findByShortUrl(shortUrl);
        if(destinationUrl.isPresent()){
            return destinationUrl.get().getDestinationUrl();
        }else{
            return null;
        }

    }


}
