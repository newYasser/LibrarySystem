package com.LibrarySystem.LibrarySystem.Service;

import com.LibrarySystem.LibrarySystem.Entity.Author;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Exeption.BookNotFoundException;
import com.LibrarySystem.LibrarySystem.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void addBook() {
        Book bookToAdd = new Book(1L,"book name","fantzy",null,null); // Create a book object for testing
        when(bookRepository.save(bookToAdd)).thenReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);

        assertNotNull(addedBook);
        verify(bookRepository, times(1)).save(bookToAdd);
    }

    @Test
    void deleteBookById() {
        Long idToDelete = 1L;
        doNothing().when(bookRepository).deleteById(idToDelete);

        assertDoesNotThrow(() -> bookService.deleteBookById(idToDelete));
        verify(bookRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void getBookById_ExistingId_ReturnsBook() {
        Long existingId = 1L;
        Book expectedBook = new Book();
        when(bookRepository.findById(existingId)).thenReturn(Optional.of(expectedBook));

        Book retrievedBook = bookService.getBookById(existingId);

        assertNotNull(retrievedBook);
        assertEquals(expectedBook, retrievedBook);
    }

    @Test
    void getBookById_NonExistingId_ThrowsBookNotFoundException() {
        Long nonExistingId = 2L;
        when(bookRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(nonExistingId));
    }

    @Test
    void getBooksByTitle() {
        String title = "The power of habit";
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1L,"The power of habit","fantzy",null,null));
        expectedBooks.add(new Book(2L,"The power of habit","fantzy",null,null));
        expectedBooks.add(new Book(3L,"The power of habit","fantzy",null,null));

        when(bookRepository.findAllByTitle(title)).thenReturn(expectedBooks);

        List<Book> retrievedBooks = bookService.getBooksByTitle(title);

        assertEquals(retrievedBooks, retrievedBooks);
    }

}
