package org.example.bms.service;

import java.util.List;

import org.example.bms.controller.AuthorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.example.bms.converter.AuthorConverter;
import org.example.bms.dao.Author;
import org.example.bms.dao.AuthorRepository;
import org.example.bms.dto.AuthorDTO;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public AuthorDTO getAuthorById(long id){
        Author author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
        return AuthorConverter.convertToAuthorDTO(author);
    }

    @Override
    public Long addNewAuthor(AuthorDTO authorDTO){
        List<Author> authorList = authorRepository.findByEmail(authorDTO.getEmail());
        if(!CollectionUtils.isEmpty(authorList)){
            throw new IllegalStateException("email" + authorDTO.getEmail() + " already exist");
        }
        Author author = authorRepository.save(AuthorConverter.convertToAuthor(authorDTO));
        return author.getId();
    }
}