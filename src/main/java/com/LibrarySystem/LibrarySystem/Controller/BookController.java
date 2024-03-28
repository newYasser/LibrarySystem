package com.LibrarySystem.LibrarySystem.Controller;

import com.LibrarySystem.LibrarySystem.DTO.BookDTO;
import com.LibrarySystem.LibrarySystem.Entity.Author;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Exeption.BookNotFoundException;
import com.LibrarySystem.LibrarySystem.Service.AuthorService;
import com.LibrarySystem.LibrarySystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO){
        Book book = new Book(null,bookDTO.getTitle(),bookDTO.getGenre(),bookDTO.getAuthor(),null );
        bookService.addBook(book);
        return ResponseEntity.ok().body("Book added successfully");
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long Id){

        try {
            Book book = bookService.getBookById(Id);
            BookDTO bookDTO = new BookDTO(book.getTitle(),book.getAuthor(),book.getGenre());
            return new ResponseEntity<>(bookDTO,HttpStatus.OK);
        }catch (BookNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/delete-book/{id}")
    public HttpStatus deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/get-books-by-genre/{genre}")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable String genre){

        List<Book>books = bookService.getBooksByGenre(genre);
        List<BookDTO>bookDTOS = new ArrayList<>();

        for(Book book : books){
            bookDTOS.add(new BookDTO(book.getTitle(),book.getAuthor(),book.getGenre()));
        }
        return new ResponseEntity<>(bookDTOS,HttpStatus.OK);
    }
    @GetMapping("/get-books-by-title/{title}")
    public ResponseEntity<List<BookDTO>> getBooksByTitle(@PathVariable String title){

        List<Book>books = bookService.getBooksByTitle(title);
        List<BookDTO>bookDTOS = new ArrayList<>();

        for(Book book : books){
            bookDTOS.add(new BookDTO(book.getTitle(),book.getAuthor(),book.getGenre()));
        }
        return new ResponseEntity<>(bookDTOS,HttpStatus.OK);
    }

    @GetMapping("/get-books-by-author/{authorName}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String authorName){
        Author author = authorService.getAuthorByName(authorName);
        List<Book>books = bookService.getBooksByAuthor(author);
        List<BookDTO>bookDTOS = new ArrayList<>();

        for(Book book : books){
            bookDTOS.add(new BookDTO(book.getTitle(),book.getAuthor(),book.getGenre()));
        }
        return new ResponseEntity<>(bookDTOS,HttpStatus.OK);
    }




}
