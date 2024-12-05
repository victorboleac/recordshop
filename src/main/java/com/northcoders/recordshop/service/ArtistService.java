package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Artist;

public interface ArtistService {

    boolean checkArtistByName(String artistName);

    Artist addArtist(Artist artist);
}
