package com.northcoders.recordshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stock_manager")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StockManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name="album_id", nullable = false, unique = true)
    Album album;

    @Column(nullable = false)
    Integer stock;

    @Column(nullable = false)
    Double price;
}