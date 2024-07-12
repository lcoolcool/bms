package org.example.bms.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse <T> {
    Integer code;
    String message;
    PageResult<T> data;
    public PageResponse(Integer code, String message, PageResult<T> data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <V> PageResponse<V> success(PageResult<V> data){
        return new PageResponse<>(20000, "success", data);
    }

}
