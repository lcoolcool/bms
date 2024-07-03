package org.example.bms.service;

import org.example.bms.converter.AuthorConverter;
import org.example.bms.dao.Author;
import org.example.bms.dao.AuthorRepository;
import org.example.bms.dto.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorDTO getAuthorById(long id){
        Author author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
        return AuthorConverter.convertToAuthorDTO(author);
    }
}