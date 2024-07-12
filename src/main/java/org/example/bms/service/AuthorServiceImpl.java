package org.example.bms.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.example.bms.dao.Book;
import org.example.bms.dao.BookRepository;
import org.example.bms.dto.AuthorBookDTO;
import org.example.bms.dto.common.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private BookRepository BookRepository;

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
    public List<AuthorDTO> getAllAuthors() {
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
        Author updateAuthor = authorRepository.save(author);
        return AuthorConverter.convertToAuthorDTO(updateAuthor);
    }

    @Override
    public PageResult<AuthorDTO> findAllOfPage(int page, int size){
        Sort sort = Sort.by(Sort.Direction.ASC, "updateTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Author> authorPage = authorRepository.findAll(pageable);
        List<AuthorDTO> authorDTOList = authorPage.getContent().stream().map(AuthorConverter::convertToAuthorDTO).toList();
        return new PageResult<>(authorPage, authorDTOList);
    }

    @Override
    public PageResult<AuthorBookDTO> findAllOfPageWithOrder(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "updateTime");
        Pageable pageable = PageRequest.of(page -1 , size, sort);
        Page<Author> authorPage = authorRepository.findAll(pageable);
        List<AuthorBookDTO> authorBookDTOPage = authorPage.getContent().stream().map(author -> {
            List<Book> books = BookRepository.listById(author.getId());
            List< AuthorBookDTO.BookDTO > bookDTOS = books.stream()
                    .map(book -> new AuthorBookDTO.BookDTO(book.getId(), book.getName(), book.getCode(), book.getDescription(), book.getAddTime()))
                    .toList();
            return new AuthorBookDTO(author.getId(), author.getName(), author.getEmail(), author.getDescription(), author.getAddTime(), author.getUpdateTime(), bookDTOS);
        }).toList();
        return new PageResult<>(authorPage, authorBookDTOPage);
    }

}