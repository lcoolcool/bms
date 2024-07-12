package org.example.bms.service;
import org.example.bms.dto.AuthorBookDTO;
import org.example.bms.dto.AuthorDTO;
import org.example.bms.dto.common.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    AuthorDTO getAuthorById(long id);

    Long addNewAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthors();

    void deleteAuthor(long id);

    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);

    PageResult<AuthorDTO> findAllOfPage(int page, int size);

    PageResult<AuthorBookDTO> findAllOfPageWithOrder(int page, int size);
}
