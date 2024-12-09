package com.northcoders.recordshop.service;

import com.northcoders.recordshop.cash.Cash;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.repository.AlbumManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AlbumServiceImpl  implements AlbumService {
    private final AlbumManagerRepository albumManagerRepository;
    private final ArtistServiceImpl artistServiceImpl;

    // NOTE: https://www.geeksforgeeks.org/why-is-field-injection-not-recommended-in-spring/

    private final Map<Long, Cash> albumCache = new HashMap<>();


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
        if(albumCache.containsKey(id)) return Optional.ofNullable((Album) albumCache.get(id).getObject());
        Optional<Album> album = albumManagerRepository.findById(id);
        album.ifPresent(a->albumCache.put(id, new Cash(a, System.currentTimeMillis())));
        return album;
    }

    @Override
    public Album updateAlbum(Album album) {
        albumCache.remove(album.getId());
        return albumManagerRepository.save(album);
    }

    //TODO: move the logic for add artist and find artist into ArtistService - DONE
    @Override
    public Album addAlbum(Album album) {
        Artist artist = album.getArtist();
        if(artistServiceImpl.checkArtistByName(artist.getName())) album.setArtist(artist);
        else album.setArtist(artistServiceImpl.addArtist(artist));
        return albumManagerRepository.save(album);
    }

    @Override
    public Optional<Album> deleteAlbumById(Long id) {
        Optional<Album> album = albumManagerRepository.findById(id);
        if(album.isPresent()) albumManagerRepository.deleteById(id);
        albumCache.remove(id);
        return album;
    }

    @Scheduled(fixedRate = 20*1000)
    public void refreshCache() {
        System.out.println("Refreshing album cache...");
     albumCache.entrySet().removeIf(entry->System.currentTimeMillis()-entry.getValue().getTimestamp()>20000);

    }



    @Override
    public List<Album> searchAlbums(String artistName, Integer releaseYear, Genre genre, String albumName) {
        if (artistName != null)  return albumManagerRepository.findByArtistName(artistName);
        if (releaseYear!= null) return  albumManagerRepository.findByReleaseYear(releaseYear);
        if (genre != null) return albumManagerRepository.findByGenre(genre);
        if (albumName!=null) return albumManagerRepository.findByNameContainingIgnoreCase(albumName);

        return new ArrayList<>();
    }


}
