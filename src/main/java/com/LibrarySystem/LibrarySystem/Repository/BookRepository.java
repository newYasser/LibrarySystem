package com.LibrarySystem.LibrarySystem.Repository;

import com.LibrarySystem.LibrarySystem.Entity.Author;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    public List<Book>findAllByTitle(String title);
    public List<Book>findAllByAuthor(Author author);
    public List<Book> findAllByGenre(String genre);
}
