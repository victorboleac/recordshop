package com.northcoders.recordshop.repository;

import com.northcoders.recordshop.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistManagerRepository extends CrudRepository<Artist, Long> {
    Optional<Artist> findByName(String name);
}
