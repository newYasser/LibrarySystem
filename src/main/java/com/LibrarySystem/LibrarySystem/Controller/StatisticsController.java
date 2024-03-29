package com.LibrarySystem.LibrarySystem.Controller;

import com.LibrarySystem.LibrarySystem.DTO.BookDTO;
import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/most-borrowed-book")
    public ResponseEntity<BookDTO>getMostBorrowedBook(){
        Book book = statisticsService.getMostBorrowdBook();
        BookDTO bookDTO = new BookDTO(book.getTitle(), book.getAuthor().getName(),book.getGenre());

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
 }
