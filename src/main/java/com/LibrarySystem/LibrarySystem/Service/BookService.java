package com.LibrarySystem.LibrarySystem.Service;

import com.LibrarySystem.LibrarySystem.Entity.Author;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Exeption.BookNotFoundException;
import com.LibrarySystem.LibrarySystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findAllByTitle(title);
    }
    public List<Book> getBooksByAuthor(Author author){
        return bookRepository.findAllByAuthor(author);
    }
    public List<Book> getBooksByGenre(String genre){
        return bookRepository.findAllByGenre(genre);
    }


}
