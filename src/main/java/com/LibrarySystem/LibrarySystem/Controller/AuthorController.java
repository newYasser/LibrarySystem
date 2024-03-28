package com.LibrarySystem.LibrarySystem.Controller;

import com.LibrarySystem.LibrarySystem.DTO.AuthorDTO;
import com.LibrarySystem.LibrarySystem.Entity.Author;
import com.LibrarySystem.LibrarySystem.Exeption.AuthorNotFoundException;
import com.LibrarySystem.LibrarySystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add-author")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorDTO authorDTO){

        if(authorDTO.getName().isEmpty() || authorDTO.getName().isBlank()){
            return new  ResponseEntity<>("name can't be empty",HttpStatus.BAD_REQUEST);
        }
        Author author = new Author();
        author.setName(authorDTO.getName());
        authorService.addAuthor(author);
        return new ResponseEntity<>("author added successfully",HttpStatus.OK);
    }
    @PutMapping("/edit-author/{id}")
    public ResponseEntity<AuthorDTO> editUser(@RequestBody AuthorDTO authorDTO, @PathVariable Long id) {
        try {
            Author author = new Author(id, authorDTO.getName(), null);
            Author editedAuthor = authorService.editAuthorById(id, author);

            AuthorDTO editedAuthorDTO = new AuthorDTO();
            editedAuthorDTO.setName(editedAuthor.getName());

            return ResponseEntity.ok(editedAuthorDTO);
        } catch (AuthorNotFoundException e) {
            // Handle AuthorNotFoundException
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-author/{id}")
    public HttpStatus deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/get-author/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id){
        try {

            Author author = authorService.getAuthorById(id);
            AuthorDTO newAuthorDTO = new AuthorDTO();
            newAuthorDTO.setName(author.getName());
            return ResponseEntity.ok(newAuthorDTO);

        } catch (AuthorNotFoundException e) {
            // Handle AuthorNotFoundException
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-all-authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        List<Author> authors = authorService.getALlAuthors();
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        for(Author author: authors){
            authorDTOS.add(new AuthorDTO(author.getName()));
        }
        return new ResponseEntity<>(authorDTOS,HttpStatus.OK);
    }



}
