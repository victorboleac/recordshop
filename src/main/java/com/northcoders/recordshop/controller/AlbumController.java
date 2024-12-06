package com.northcoders.recordshop.controller;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.AlbumManagerRepository;
import com.northcoders.recordshop.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumManagerRepository albumManagerRepository;

    public AlbumController(AlbumService albumService, AlbumManagerRepository albumManagerRepository) {
        this.albumService = albumService;
        this.albumManagerRepository = albumManagerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        try {
            List<Album> albums = albumService.getAllAlbums();
            if (albums == null) {
                albums = new ArrayList<>();
            }
            return ResponseEntity.ok(albums);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        try {
            if(album==null) return ResponseEntity.badRequest().build();

            Album newAlbum = albumService.addAlbum(album);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAlbum);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
        public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        if (albumService.getAlbumById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (Objects.equals(album.getId(), id)) return new ResponseEntity<>(albumService.updateAlbum(album),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
