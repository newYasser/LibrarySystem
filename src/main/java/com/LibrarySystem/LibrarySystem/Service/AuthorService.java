package com.LibrarySystem.LibrarySystem.Service;

import com.LibrarySystem.LibrarySystem.Entity.Author;
import com.LibrarySystem.LibrarySystem.Exeption.AuthorNotFoundException;
import com.LibrarySystem.LibrarySystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long Id){
         authorRepository.deleteById(Id);
    }
    public Author getAuthorById(Long Id){
        Optional<Author> authorOptional = authorRepository.findById(Id);

        try{
            return authorOptional.orElseThrow(() -> new AuthorNotFoundException("Author not found with ID: " + Id));
        }catch (NullPointerException e){
            throw  e;
        }
    }

    public Author editAuthorById(Long Id,Author oldAuthorData){
        Optional<Author> authorOptional = authorRepository.findById(Id);
        try{
            authorOptional.orElseThrow(() -> new AuthorNotFoundException("Author not found with ID: " + Id));
            Author newAuthorData = new Author(Id, oldAuthorData.getName(),null);
            authorRepository.save(newAuthorData);
            return  authorRepository.save(newAuthorData);
        }catch (NullPointerException e){
            throw  e;
        }
    }

    public List<Author> getALlAuthors(){
        return authorRepository.findAll();
    }

}
