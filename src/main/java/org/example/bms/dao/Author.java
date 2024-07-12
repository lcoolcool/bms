package org.example.bms.dao;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private String description;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "add_time", updatable = false)
    private LocalDateTime addTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @PrePersist
    protected   void  onCreate(){
        this.addTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    @PreUpdate
    private  void  onUpdate(){
        this.updateTime = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;
}