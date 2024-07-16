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
    public Response(int code, String message, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <V> Response<V> success(){
        return new Response<>(20000, "success");
    }

    public static <V> Response<V> success(V data){
        return new Response<>(20000, "success", data);
    }

    public static <V> Response<V> fail(){
        return new Response<>(50000, "fail");
    }

    public static <V> Response<V> fail(String message){
        return new Response<>(50000, message);
    }

    public static <V> Response<V> fail(int code, String message){
        return new Response<>(code, message);
    }

    public static <V> Response<V> fail(int code, String message, V data){
        return new Response<>(code, message, data);
    }

    public boolean isOk(){
        return this.code == 20000;
    }

    public boolean isFail(){
        return this.code != 20000;
    }
}
