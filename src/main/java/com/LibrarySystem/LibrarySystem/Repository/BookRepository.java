package com.LibrarySystem.LibrarySystem.Repository;

import com.LibrarySystem.LibrarySystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
