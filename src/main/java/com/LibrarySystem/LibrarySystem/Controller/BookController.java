package com.LibrarySystem.LibrarySystem.Controller;

import com.LibrarySystem.LibrarySystem.DTO.BookDTO;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Service.BookService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add-book")
    public Book addBook(@RequestBody BookDTO bookDTO){
        Book book = new Book(null,bookDTO.getTitle(),bookDTO.getGenre(),bookDTO.getAuthor(),null );
        return bookService.addBook(book);
    }


}
