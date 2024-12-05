package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> getAllAlbums();
    Optional<Album> getAlbumById(Long id);
    Album addAlbum(Album album);
    Album updateAlbum(Album album);
    Optional<Album> deleteAlbumById(Long id);

}
