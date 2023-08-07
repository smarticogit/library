package com.domvs.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "available_quantity")
    private Long availableQuantity;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
}
