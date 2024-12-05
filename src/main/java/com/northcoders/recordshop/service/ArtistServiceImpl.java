package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Artist;
import com.northcoders.recordshop.repository.ArtistManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistManagerRepository artistManagerRepository;

    @Override
    public boolean checkArtistByName(String artistName) {
        return artistManagerRepository.findByName(artistName)
                .isPresent();
    }

    @Override
    public Artist addArtist(Artist artist) {
        return artistManagerRepository.save(artist);
    }
}
