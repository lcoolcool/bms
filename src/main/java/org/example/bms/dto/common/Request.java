package org.example.bms.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request <T>{
    /**
     * 筛选条件
     */
    private T filter;
}
