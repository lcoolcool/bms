package org.example.bms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByEmail(String email);
    List<Author> findByEmailAndIdNot(String email, Long id);
}
