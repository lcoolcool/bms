package org.example.bms.controller;

import org.example.bms.Response;
import org.example.bms.dto.AuthorDTO;
import org.example.bms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService authorService;

    @GetMapping("/author/{id}")
    public Response<AuthorDTO> getAuthorById(@PathVariable long id){
        return Response.success(authorService.getAuthorById(id));
    }
}
