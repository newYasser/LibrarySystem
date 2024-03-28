package com.LibrarySystem.LibrarySystem.Exeption;

public class BookNotBorrowedException extends RuntimeException {

    public BookNotBorrowedException(String message) {
        super(message);
    }
}
