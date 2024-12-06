package com.northcoders.recordshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="albums")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Genre genre;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = false)
    Artist artist;


}
