package com.northcoders.recordshop.controller;


import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AlbumControllerTest {
    @Mock
    private AlbumService albumService;

    @InjectMocks
    private AlbumController albumController;

    @Test
    @DisplayName("GET /api/v1/albums - Returns a list of albums")
    void testGetAllAlbums_ReturnsAlbums() {
        // Arrange
        List<Album> expectedAlbums = new ArrayList<>();
        expectedAlbums.add(new Album(1L, "The Razors Edge", 1990,
                Genre.ROCK, new Artist(1L, "AC/DC", "Australia")));
        expectedAlbums.add(new Album(2L, "Never Mind the Bollocks, Here's the Sex Pistols", 1977,
                Genre.ROCK, new Artist(2L, "Sex Pistols", "United Kingdom")));

        when(albumService.getAllAlbums()).thenReturn(expectedAlbums);

        // Act
        ResponseEntity<List<Album>> response = albumController.getAllAlbums();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAlbums, response.getBody());
    }
}