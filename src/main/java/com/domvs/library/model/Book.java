package com.domvs.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "available_quantity")
    private Long availableQuantity;

    @Column(name = "number_pages")
    private int numberOfPages;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
}
