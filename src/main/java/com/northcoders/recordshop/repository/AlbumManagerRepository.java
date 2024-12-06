package com.northcoders.recordshop.repository;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumManagerRepository extends CrudRepository<Album, Long> {
    List<Album> findByArtistName(String artistName);
    List<Album> findByReleaseYear(Integer releaseYear);
    List<Album> findByGenre(Genre genre);
    List<Album> findByNameContainingIgnoreCase(String albumName);

}
