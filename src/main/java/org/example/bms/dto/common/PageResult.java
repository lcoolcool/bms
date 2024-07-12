package org.example.bms.dto.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResult <T> {
     private int current_page;
     private int total_pages;
     private long count;
     private List<T> results;

     public <U> PageResult(Page<U> page, List<T> content){
          this.results = content;
          this.current_page = page.getNumber();
          this.total_pages = page.getTotalPages();
          this.count = page.getTotalElements();
     }
}
