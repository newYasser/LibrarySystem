package com.LibrarySystem.LibrarySystem.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String genre;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<BookTransaction> transactions;
}
