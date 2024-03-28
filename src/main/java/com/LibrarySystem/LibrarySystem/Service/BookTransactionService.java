package com.LibrarySystem.LibrarySystem.Service;

import com.LibrarySystem.LibrarySystem.Entity.Book;
import com.LibrarySystem.LibrarySystem.Entity.BookTransaction;
import com.LibrarySystem.LibrarySystem.Exeption.BookNotFoundException;
import com.LibrarySystem.LibrarySystem.Repository.BookRepository;
import com.LibrarySystem.LibrarySystem.Repository.BookTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookTransactionService {

    @Autowired
    private BookTransactionRepository bookTransactionRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookTransaction borrowBookWithId(Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            BookTransaction bookTransaction = new BookTransaction(null,bookOptional.get(), LocalDate.now(),null);
            return bookTransactionRepository.save(bookTransaction);
        }
        return null;
    }


    public BookTransaction returnBook(Long id){
        Optional<BookTransaction> bookTransactionOptional = bookTransactionRepository.findById(id);
        if(bookTransactionOptional.isPresent()){
            BookTransaction bookTransaction = bookTransactionOptional.get();
            bookTransaction.setReturnDate(LocalDate.now());
            return bookTransactionRepository.save(bookTransaction);
        }
        return null;
    }

    public List<BookTransaction> getAllTransactions(){
        return bookTransactionRepository.findAll();
    }

}
