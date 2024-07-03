package org.example.bms;

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

    public static <K> Response<K> success(){
        return new Response<>(200, "success");
    }

    public static <K> Response<K> success(K data){
        return new Response<>(data, 20000, "success");
    }

    public static <K> Response<K> fail(){
        return new Response<>(50000, "fail");
    }

    public static <K> Response<K> fail(String message){
        return new Response<>(50000, message);
    }

}
