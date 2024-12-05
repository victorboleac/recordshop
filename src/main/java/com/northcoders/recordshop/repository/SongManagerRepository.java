package com.northcoders.recordshop.repository;

import com.northcoders.recordshop.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongManagerRepository extends CrudRepository <Song, Long>{
}
