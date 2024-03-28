package com.LibrarySystem.LibrarySystem.Controller;

import com.LibrarySystem.LibrarySystem.Entity.BookTransaction;
import com.LibrarySystem.LibrarySystem.Exeption.BookNotBorrowedException;
import com.LibrarySystem.LibrarySystem.Exeption.BookNotFoundException;
import com.LibrarySystem.LibrarySystem.Service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class BookTransactionController {

    @Autowired
    private BookTransactionService bookTransactionService;

    @PostMapping("/borrow-book/{id]")
    public HttpStatus borrowBook(@PathVariable Long id){
       try {
           bookTransactionService.borrowBookWithId(id);
           return HttpStatus.OK;
       }catch (BookNotFoundException e){
           return HttpStatus.NOT_FOUND;
       }catch (Exception e) {
           return HttpStatus.INTERNAL_SERVER_ERROR;
       }
    }

    @PutMapping("/return-book/{id}")
    public HttpStatus returnBook(@PathVariable Long id){
        try {
            bookTransactionService.returnBook(id);
            return HttpStatus.OK;
        }catch (BookNotBorrowedException e){
            return HttpStatus.NOT_FOUND;
        }catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/get-all-transactions")
    public ResponseEntity<List<BookTransaction>> getAllTransactions(){
        return new ResponseEntity<>(bookTransactionService.getAllTransactions(),HttpStatus.OK);
    }
}
