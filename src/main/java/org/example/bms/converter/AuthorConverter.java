package org.example.bms.converter;

import org.example.bms.dao.Author;
import org.example.bms.dto.AuthorDTO;

import java.time.format.DateTimeFormatter;

public class AuthorConverter {

    public static AuthorDTO convertToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setDescription(author.getDescription());
        authorDTO.setAdd_time(author.getAdd_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return authorDTO;
    }
}
