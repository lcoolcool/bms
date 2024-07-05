package org.example.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.example.bms.controller.AuthorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.example.bms.converter.AuthorConverter;
import org.example.bms.dao.Author;
import org.example.bms.dao.AuthorRepository;
import org.example.bms.dto.AuthorDTO;
import org.springframework.util.StringUtils;


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

    @Override
    public List<AuthorDTO> getAllAuthors(String name, String email, String add_time_range) {
        String[] add_time_range_list = StringUtils.split(add_time_range, ",");
        String start_add_time = null;
        String end_add_time = null;
        if (add_time_range_list != null) {
            start_add_time = add_time_range_list[0];
            end_add_time = add_time_range_list[1];
        }
        System.out.println("start_add_time: %s, end_add_time:%s".formatted(start_add_time, end_add_time));
        List<Author> authorList = authorRepository.findAll();
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for (Author author : authorList) {
            AuthorDTO authorDTO = AuthorConverter.convertToAuthorDTO(author);
            authorDTOList.add(authorDTO);
        }
        return authorDTOList;
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.findById(id).orElseThrow(RuntimeException::new);
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
        List<Author> authorList = authorRepository.findByEmailAndIdNot(authorDTO.getEmail(), id);
        if(!CollectionUtils.isEmpty(authorList)){
            throw new IllegalStateException("email" + authorDTO.getEmail() + " already exist");
        }
        author.setEmail(authorDTO.getEmail());
        author.setName(authorDTO.getName());
        author.setDescription(authorDTO.getDescription());
        author.setAdd_time(author.getAdd_time());
        authorRepository.save(author);
        return AuthorConverter.convertToAuthorDTO(author);
    }

}