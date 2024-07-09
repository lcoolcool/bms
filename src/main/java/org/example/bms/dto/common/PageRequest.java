package org.example.bms.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest<T> extends Request<T> {
    private int page;
    private int size;
    public PageRequest(T filter, int page, int size)
    {
        this.setFilter(filter);
        this.page = page;
        this.size = size;
    }
}
