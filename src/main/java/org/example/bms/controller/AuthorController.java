package org.example.bms.controller;

import org.example.bms.Response;
import org.example.bms.dto.AuthorDTO;
import org.example.bms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService authorService;

    @GetMapping("/author/{id}")
    public Response<AuthorDTO> getAuthorById(@PathVariable long id){
        return Response.success(authorService.getAuthorById(id));
    }

    @PostMapping("/author")
    public Response<Long> createAuthor(@RequestBody AuthorDTO authorDTO){
        return Response.success(authorService.addNewAuthor(authorDTO));
    }
}
