package com.LibrarySystem.LibrarySystem.Exeption;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
