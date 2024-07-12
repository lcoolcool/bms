package org.example.bms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AuthorBookDTO {

    private long id;

    private String name;

    private String email;

    private String description;

    private String addTime;

    private String updateTime;

    private List<BookDTO> books;

    public AuthorBookDTO(Long authorId, String authorName, String authorEmail, String authorDescription,
                         LocalDateTime authorAddTime, LocalDateTime authorUpdateTime, List<BookDTO> books)
    {
        this.id = authorId;
        this.name = authorName;
        this.email = authorEmail;
        this.description = authorDescription;
        this.addTime = authorAddTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updateTime = authorUpdateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.books = books;
    }

    @Getter
    @Setter
    public static class BookDTO {
        private Long id;
        private String name;
        private String code;
        private String description;
        private String add_time;

        public BookDTO(Long bookId, String bookName, String bookCode, String bookDescription, LocalDateTime addTime)
        {
            this.id = bookId;
            this.name = bookName;
            this.code = bookCode;
            this.description = bookDescription;
            this.add_time = addTime == null ? "" : addTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
