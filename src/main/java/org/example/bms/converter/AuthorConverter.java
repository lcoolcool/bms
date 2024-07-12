package org.example.bms.converter;

import org.example.bms.dao.Author;
import org.example.bms.dto.AuthorDTO;

import java.time.format.DateTimeFormatter;

public class AuthorConverter {

    public static AuthorDTO convertToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setDescription(author.getDescription());
        authorDTO.setAdd_time(author.getAddTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        authorDTO.setUpdate_time(author.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return authorDTO;
    }

    public static Author convertToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setDescription(authorDTO.getDescription());
        return author;
    }
}
