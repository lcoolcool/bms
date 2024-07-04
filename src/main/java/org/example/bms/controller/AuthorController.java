package org.example.bms.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import org.example.bms.Response;
import org.example.bms.dto.AuthorDTO;
import org.example.bms.service.AuthorService;

@RestController
public class AuthorController {

    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService authorService;

    /**
     * 查看作者详情
     * @param id
     * @return
     */
    @GetMapping("/author/{id}")
    public Response<AuthorDTO> getAuthorById(@PathVariable long id){
        return Response.success(authorService.getAuthorById(id));
    }

    /**
     * 创建作者
     * @serialData  authorDTO
     * @return
     */
    @PostMapping("/author")
    public Response<Long> createAuthor(@RequestBody AuthorDTO authorDTO){
        try {
            return Response.success(authorService.addNewAuthor(authorDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail(e.getMessage());
        }
    }

    /**
     *  作者列表
     * @param name
     * @param email
     * @param add_time_range
     * @return
     */
    @GetMapping("/author")
    public Response<List<AuthorDTO>> getAllAuthors(@RequestParam(required = false) String name
    , @RequestParam(required = false) String email, @RequestParam(required = false) String add_time_range){
        return Response.success(authorService.getAllAuthors(name, email, add_time_range));
    }

    /**
     *  删除作者
     * @param id
     * @return
     */
    @DeleteMapping("/author")
    public Response<String> deleteAuthor(@PathVariable long id){
        return Response.success(authorService.deleteAuthor(id));
    }
}
