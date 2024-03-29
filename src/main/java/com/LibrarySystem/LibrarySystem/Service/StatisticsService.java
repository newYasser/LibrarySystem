package com.LibrarySystem.LibrarySystem.Service;

import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Repository.BookRepository;
import com.LibrarySystem.LibrarySystem.Repository.BookTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookTransactionRepository bookTransactionRepository;

    public Book getMostBorrowdBook(){

        Long mostBorrowedBookId = bookTransactionRepository.findMostBorrowedBook();
        Book book = bookRepository.getReferenceById(mostBorrowedBookId);
        return book;
    }
}
