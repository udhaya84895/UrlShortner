package com.example.urlshortener.controllerTest;

import com.example.urlshortener.controller.UrlMapperController;
import com.example.urlshortener.service.UrlMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UrlMapperControllerTest {

    @Mock
    private UrlMapperService urlMapperService;

    @InjectMocks
    private UrlMapperController urlMapperController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShortenUrl() {
        // Given
        String destinationUrl = "https://example.com";
        String mockShortUrl = "http://short.url/xyz";
        when(urlMapperService.shortenUrl(destinationUrl)).thenReturn(mockShortUrl);

        // When
        ResponseEntity<?> responseEntity = urlMapperController.shortenUrl(destinationUrl);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockShortUrl, responseEntity.getBody());
        verify(urlMapperService, times(1)).shortenUrl(destinationUrl);
        verifyNoMoreInteractions(urlMapperService);
    }

    @Test
    public void testGetDestinationUrl() {
        // Given
        String shortUrl = "http://short.url/xyz";
        String mockDestinationUrl = "https://example.com";
        when(urlMapperService.getDestinationUrl(shortUrl)).thenReturn(mockDestinationUrl);

        // When
        String actualDestinationUrl = urlMapperController.getDestinationUrl(shortUrl);

        // Then
        assertEquals(mockDestinationUrl, actualDestinationUrl);
        verify(urlMapperService, times(1)).getDestinationUrl(shortUrl);
        verifyNoMoreInteractions(urlMapperService);
    }

    @Test
    public void testGetDestinationUrl_NoUrlFound() {
        // Given
        String shortUrl = "http://short.url/xyz";
        when(urlMapperService.getDestinationUrl(shortUrl)).thenReturn(null);

        // When
        String actualDestinationUrl = urlMapperController.getDestinationUrl(shortUrl);

        // Then
        assertEquals("NoUrl found", actualDestinationUrl);
        verify(urlMapperService, times(1)).getDestinationUrl(shortUrl);
        verifyNoMoreInteractions(urlMapperService);
    }
}
