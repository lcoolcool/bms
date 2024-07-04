package org.example.bms.service;
import org.example.bms.dto.AuthorDTO;

public interface AuthorService {

    AuthorDTO getAuthorById(long id);

    Long addNewAuthor(AuthorDTO authorDTO);
}
