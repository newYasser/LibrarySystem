package com.LibrarySystem.LibrarySystem.Repository;

import com.LibrarySystem.LibrarySystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
