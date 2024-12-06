package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.repository.AlbumManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlbumServiceImplTest {
    @Mock
    private AlbumManagerRepository albumManagerRepository;
    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("getAllAlbums() returns list of albums")
    void testGetAllAlbums() {
        //ARRANGE

        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Thriller", 1982, Genre.POP, new Artist(1L, "Michael Jackson", "USA")));
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


}

