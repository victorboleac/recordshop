package com.northcoders.recordshop.service;


import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.repository.ArtistManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ArtistServiceImplTest {
    @Mock
    private ArtistManagerRepository artistManagerRepository;
    @InjectMocks
    private ArtistServiceImpl artistServiceImpl;

    @Test
    @DisplayName("checkArtistByName() returns true when an artist exists")
    void testCheckArtistByName_ArtistExists() {
        // Arrange
        String artistName = "AC/DC";
        Artist existingArtist = new Artist(1L, artistName, "Australia");
        when(artistManagerRepository.findByName(artistName)).thenReturn(Optional.of(existingArtist));

        // Act
        boolean result = artistServiceImpl.checkArtistByName(artistName);

        // Assert
        assertThat(result).isTrue();
        verify(artistManagerRepository, times(1)).findByName(artistName);
    }

    @Test
    @DisplayName("checkArtistByName() returns false when artist name is null")
    void testCheckArtistByName_NullInput() {
        //Act
        boolean result = artistServiceImpl.checkArtistByName(null);

        // ASSERT
        assertThat(result).isFalse();
        verify(artistManagerRepository, never()).findByName(any());
    }

    @Test
    @DisplayName("checkArtistByName() returns false when artist does not exist")
    void testCheckArtistByName_ArtistDoesNotExist() {
        // Arrange
        String artistName = "Sex Pistols";
        when(artistManagerRepository.findByName(artistName)).thenReturn(Optional.empty());

        // Act
        boolean result = artistServiceImpl.checkArtistByName(artistName);

        // Assert
        assertThat(result).isFalse();
        verify(artistManagerRepository, times(1)).findByName(artistName);
    }

    @Test
    @DisplayName("addArtist() adds a new artist")
    void testAddArtist() {
        // Arrange
        Artist newArtist = new Artist(null, "AC/DC", "Australia");
        Artist savedArtist = new Artist(1L, "AC/DC", "Australia");
        when(artistManagerRepository.save(newArtist)).thenReturn(savedArtist);

        // Act
        Artist result = artistServiceImpl.addArtist(newArtist);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("AC/DC");
        assertThat(result.getCountry()).isEqualTo("Australia");
        verify(artistManagerRepository, times(1)).save(newArtist);
    }
}