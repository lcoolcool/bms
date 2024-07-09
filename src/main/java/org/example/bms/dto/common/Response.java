package org.example.bms.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response <T> {

    private T data;

    private int code;

    private String message;

    public Response(int code, String message){
        this.code = code;
        this.message = message;
    }
    public Response(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <V> Response<V> success(){
        return new Response<>(200, "success");
    }

    public static <V> Response<V> success(V data){
        return new Response<>(data, 20000, "success");
    }

    public static <V> Response<V> fail(){
        return new Response<>(50000, "fail");
    }

    public static <V> Response<V> fail(String message){
        return new Response<>(50000, message);
    }

}
