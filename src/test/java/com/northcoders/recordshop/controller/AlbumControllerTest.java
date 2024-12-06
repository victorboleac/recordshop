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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


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
    @Test
    @DisplayName("GET /api/v1/albums - Returns an empty list ")
    public void testGetAllAlbums_ReturnsEmptyList() {
        // Arrange
        List<Album> expectedAlbums = new ArrayList<>();
        when(albumService.getAllAlbums()).thenReturn(expectedAlbums);

        // Act
        ResponseEntity<List<Album>> response = albumController.getAllAlbums();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAlbums, response.getBody());
    }

    @Test
    @DisplayName("GET /api/v1/albums - converts null response from service")
    void testGetAllAlbums_ServiceReturnsNull() {
        // Arrange
        when(albumService.getAllAlbums()).thenReturn(null);

        // Act
        ResponseEntity<List<Album>> response = albumController.getAllAlbums();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    @DisplayName("GET /api/v1/albums - Handles service exception ")
    void testGetAllAlbums_ServiceException() {
        // Arrange
        when(albumService.getAllAlbums()).thenThrow(new RuntimeException("DB not working"));

        // Act
        ResponseEntity<List<Album>> response;
        try {
            response = albumController.getAllAlbums();
        } catch (RuntimeException ex) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    @DisplayName("GET /api/v1/albums/{id} - returns the album if found by id")
    void testGetAlbumById_ReturnsAlbumIfFoundById() {

        //Arrange
        Long albumId = 1L;
        Album resultAlbum = new Album(albumId, "The Razors Edge", 1990, Genre.ROCK,
                new Artist(1L, "AC/DC", "Australia"));
        when(albumService.getAlbumById(albumId)).thenReturn(Optional.of(resultAlbum));
        //Act
        ResponseEntity<Album> response = albumController.getAlbumById(albumId);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultAlbum, response.getBody());
    }

    @Test
    @DisplayName("GET /api/v1/albums/{id}  returns 404 when the album is not found")
    void testGetAlbumById_Returns404WhenTheAlbumIsNotFound() {
        //Arrange
        Long albumId = 1L;
        when(albumService.getAlbumById(albumId)).thenReturn(Optional.empty());

        //ACT
        ResponseEntity<Album> response = albumController.getAlbumById(albumId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("GET /api/v1/albums/{id} - with invalid id, returns 404")
    void testGetAlbumById_WithInvalidId() {
        // Arrange
        Long albumId = -1L;
        when(albumService.getAlbumById(albumId)).thenReturn(Optional.empty());

        //ACT
        ResponseEntity<Album> response = albumController.getAlbumById(albumId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("POST /api/v1/albums - creates new album")
    void testPostAlbums_CreatesNewAlbum() {
        //arrange
        Album album =  new Album(null, "The Razors Edge", 1990, Genre.ROCK,
                new Artist(1L, "AC/DC", "Australia"));
        Album createdAlbum = new Album(1L, "The Razors Edge", 1990, Genre.ROCK,
                new Artist(1L, "AC/DC", "Australia"));

        when(albumService.addAlbum(album)).thenReturn(createdAlbum);

        //act
        ResponseEntity<Album> result = albumController.createAlbum(album);

        //assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(createdAlbum, result.getBody());

    }

    @Test
    @DisplayName("POST /api/v1/albums -  null album input")
    void testCreateAlbum_NullInput() {
        // Act
        ResponseEntity<Album> response = albumController.createAlbum(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}