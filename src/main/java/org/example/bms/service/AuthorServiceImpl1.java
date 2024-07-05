package org.example.bms.service;

import org.example.bms.converter.AuthorConverter;
import org.example.bms.dao.Author;
import org.example.bms.dto.AuthorDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl1 implements AuthorService {

    @Override
    public AuthorDTO getAuthorById(long id){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(id);
        return authorDTO;
    };

    @Override
    public Long addNewAuthor(AuthorDTO authorDTO){
        Author author = new Author();
        return 2L;
    }

    @Override
    public List<AuthorDTO> getAllAuthors(String name, String email, String add_time_range) {
        return new ArrayList<>();
    }

    @Override
    public void deleteAuthor(long id) {}

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        return new AuthorDTO();
    }
}
