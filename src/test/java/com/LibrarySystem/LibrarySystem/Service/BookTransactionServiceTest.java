package com.LibrarySystem.LibrarySystem.Service;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Entity.BookTransaction;
import com.LibrarySystem.LibrarySystem.Repository.BookRepository;
import com.LibrarySystem.LibrarySystem.Repository.BookTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookTransactionServiceTest {

    @Mock
    private BookTransactionRepository bookTransactionRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookTransactionService bookTransactionService;

    private Book book;

    @BeforeEach
    void creatBook(){
        Book book = new Book(null,"book name","fantzy",null,null);
        bookRepository.save(book);
    }

    @Test
    void borrowBookWithId_BookExists_ReturnsBookTransaction() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookTransaction savedTransaction = new BookTransaction();
        when(bookTransactionRepository.save(any())).thenReturn(savedTransaction);

        BookTransaction result = bookTransactionService.borrowBookWithId(bookId);

        assertEquals(savedTransaction, result);
        verify(bookTransactionRepository, times(1)).save(any());
    }

    @Test
    void borrowBookWithId_BookDoesNotExist_ReturnsNull() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookTransaction result = bookTransactionService.borrowBookWithId(bookId);

        assertEquals(null, result);
        verify(bookTransactionRepository, never()).save(any());
    }



    @Test
    void returnBook_TransactionNotFound_ReturnsNull() {
        Long transactionId = 1L;
        when(bookTransactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        BookTransaction result = bookTransactionService.returnBook(transactionId);

        assertEquals(null, result);
        verify(bookTransactionRepository, never()).save(any());
    }
}
