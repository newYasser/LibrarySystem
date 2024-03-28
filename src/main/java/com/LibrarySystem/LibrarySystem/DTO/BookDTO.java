package com.LibrarySystem.LibrarySystem.DTO;


import com.LibrarySystem.LibrarySystem.Entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private Author author;
    private String genre;
}
