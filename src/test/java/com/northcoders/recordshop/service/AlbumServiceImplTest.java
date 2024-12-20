package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.repository.AlbumManagerRepository;
import com.northcoders.recordshop.repository.ArtistManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlbumServiceImplTest {
    @Mock
    private AlbumManagerRepository albumManagerRepository;

    @Mock
    private ArtistServiceImpl artistServiceImpl;


    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;



    @Test
    @DisplayName("getAllAlbums() returns list of albums")
    void testGetAllAlbums() {
        //ARRANGE

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "The Razors Edge", 1990, Genre.ROCK,
                new Artist(1L, "AC/DC", "Australia")));
        when(albumManagerRepository.findAll()).thenReturn(albums);

        //ACT
        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        //ASSERT
        assertThat(actualResult).hasSize(1);
        assertThat(actualResult).isEqualTo(albums);

    }

    @Test
    @DisplayName("getAllAlbums() returns an empty list when there are no albums ")
    void testGetAllAlbums_NoAlbums() {
        // ARRANGE
        when(albumManagerRepository.findAll()).thenReturn(new ArrayList<>());

        // ACT
        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        // ASSERT
        assertThat(actualResult).isEmpty();
    }

    @Test
    @DisplayName("getAlbumById() returns Optional (empty) when the album does not exist")
    void testGetAlbumById_NoAlbum(){

        //ARRANGE
        Long albumId = 1L;
        when(albumManagerRepository.findById(albumId)).thenReturn(Optional.empty());

        //ACT
        Optional<Album> actualResult = albumServiceImpl.getAlbumById(albumId);

        //ASSERT
        assertThat(actualResult).isEmpty();

    }

    @Test
    @DisplayName("deleteAlbumById() returns Optional (empty) when the album does not exist")
    void testDeleteAlbumById_NoAlbum() {
        //ARRANGE
        Long albumId = 1L;
        when(albumManagerRepository.findById(albumId)).thenReturn(Optional.empty());

        //ACT
        Optional<Album> actualResult = albumServiceImpl.deleteAlbumById(albumId);
        //ASEERT
        assertThat(actualResult).isEmpty();
        verify(albumManagerRepository, times(0)).deleteById(albumId);

    }

    @Test
    @DisplayName("deleteAlbumById() deletes an album when it exists")
    void testDeleteAlbumById_AlbumExists() {
        // ARRANGE
        Long albumId = 1L;
        Album existingAlbum = new Album(albumId, "The Razors Edge", 1990, Genre.ROCK,
                new Artist(1L, "AC/DC", "Australia"));

        when(albumManagerRepository.findById(albumId)).thenReturn(Optional.of(existingAlbum));

        // ACT
        Optional<Album> deletedAlbum = albumServiceImpl.deleteAlbumById(albumId);

        // ASSERT
        assertThat(deletedAlbum).isPresent();
        assertThat(deletedAlbum.get()).isEqualTo(existingAlbum);
        verify(albumManagerRepository, times(1)).deleteById(albumId);
    }

    @Test
    void testAddAlbum_ExistingArtist() {
        Artist artist = new Artist();
        artist.setName("Existing Artist");

        Album album = new Album();
        album.setArtist(artist);

        when(artistServiceImpl.checkArtistByName("Existing Artist")).thenReturn(true);

        when(albumManagerRepository.save(album)).thenReturn(album);

        Album result = albumServiceImpl.addAlbum(album);

        assertEquals(artist, result.getArtist());
        verify(artistServiceImpl, times(1)).checkArtistByName("Existing Artist");
        verify(albumManagerRepository, times(1)).save(album);
    }
}

