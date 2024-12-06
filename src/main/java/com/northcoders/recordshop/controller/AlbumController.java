package com.northcoders.recordshop.controller;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.AlbumManagerRepository;
import com.northcoders.recordshop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AlbumController(AlbumService albumService, AlbumManagerRepository albumManagerRepository) {
        this.albumService = albumService;
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
            if (album == null) return ResponseEntity.badRequest().build();

            Album newAlbum = albumService.addAlbum(album);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAlbum);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        if (albumService.getAlbumById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (Objects.equals(album.getId(), id))
            return new ResponseEntity<>(albumService.updateAlbum(album), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        if (albumService.deleteAlbumById(id).isPresent())
            return new ResponseEntity<>("Album with id: " + id + " was deleted", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/albums/search")
    public ResponseEntity<?> searchAlbums(@RequestParam(required = false) String artistName,
                                          @RequestParam(required = false) Integer releaseYear,
                                          @RequestParam(required = false) Genre genre,
                                          @RequestParam(required = false) String albumName) {
        List<Album> albums = albumService.searchAlbums(artistName, releaseYear, genre, albumName);

        if (albums.isEmpty())
            return new ResponseEntity<>("There is no result for your search", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(albums);

    }
}



/*
6. /api/v1/albums/search (GET) parameter: artist
7. /api/v1/albums/search (GET) parameter: releaseYear
8. /api/v1/albums/search (GET) parameter: genre
9. /api/v1/albums/search (GET) parameter: albumName
10. /api/v1/health
 */
