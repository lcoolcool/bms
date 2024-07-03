package org.example.bms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/hello")
    public List<String> hello(){
        return List.of("hello", "world");
    }
    Date tt  = new Date() ;
}
