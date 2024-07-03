package org.example.bms.service;

import org.example.bms.dto.AuthorDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl1 implements AuthorService {

    @Override
    public AuthorDTO getAuthorById(long id){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(id);
        return authorDTO;
    };
}
