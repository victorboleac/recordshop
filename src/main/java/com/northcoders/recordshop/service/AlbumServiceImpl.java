package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.repository.AlbumManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl  implements AlbumService {

// NOTE: https://www.geeksforgeeks.org/why-is-field-injection-not-recommended-in-spring/

    private final AlbumManagerRepository albumManagerRepository;
    private final ArtistServiceImpl artistServiceImpl;

    @Autowired
    public AlbumServiceImpl(AlbumManagerRepository albumManagerRepository, ArtistServiceImpl artistServiceImpl) {
        this.albumManagerRepository = albumManagerRepository;
        this.artistServiceImpl = artistServiceImpl;
    }


    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumManagerRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        return albumManagerRepository.findById(id);
    }

    @Override
    public Album updateAlbum(Album album) {
        return albumManagerRepository.save(album);
    }

    //TODO: move the logic for add artist and find artist into ArtistService - DONE
    @Override
    public Album addAlbum(Album album) {
        Artist artist = album.getArtist();
        if(artistServiceImpl.checkArtistByName(artist.getName())) album.setArtist(artist);
        else album.setArtist(artistServiceImpl.addArtist(artist));
        return updateAlbum(album);
    }

    @Override
    public Optional<Album> deleteAlbumById(Long id) {
        Optional<Album> album = albumManagerRepository.findById(id);
        if(album.isPresent()) albumManagerRepository.deleteById(id);
        return album;
    }
}
