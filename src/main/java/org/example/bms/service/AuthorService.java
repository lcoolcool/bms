package org.example.bms.service;
import org.example.bms.dto.AuthorDTO;

import java.util.List;
import java.util.Objects;

public interface AuthorService {

    AuthorDTO getAuthorById(long id);

    Long addNewAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthors(String name, String email, String addTimeRange);

    void deleteAuthor(long id);
}
