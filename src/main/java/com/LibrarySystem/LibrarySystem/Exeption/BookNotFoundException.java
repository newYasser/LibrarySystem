package com.LibrarySystem.LibrarySystem.Exeption;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
