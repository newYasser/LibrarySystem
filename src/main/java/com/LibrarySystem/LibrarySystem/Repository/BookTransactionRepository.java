package com.LibrarySystem.LibrarySystem.Repository;

import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction,Long> {

    @Query(value = "SELECT book_id FROM book_transaction bt GROUP BY book_id ORDER BY COUNT(book_id) DESC LIMIT 1", nativeQuery = true)
    public Long findMostBorrowedBook();


}
