package com.LibrarySystem.LibrarySystem.Repository;

import com.LibrarySystem.LibrarySystem.Entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction,Long> {
}
