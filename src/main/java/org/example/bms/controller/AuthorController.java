package org.example.bms.controller;

import java.util.List;

import lombok.Setter;
import org.example.bms.dto.AuthorBookDTO;
import org.example.bms.dto.common.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.web.bind.annotation.*;

import org.example.bms.dto.common.Response;
import org.example.bms.dto.AuthorDTO;
import org.example.bms.service.AuthorService;

@RestController
public class AuthorController {

    @Autowired
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
     * @return
     */
    @GetMapping("/author")
    public Response<List<AuthorDTO>> getAllAuthors(){
        return Response.success(authorService.getAllAuthors());
    }

    /**
     *  删除作者
     * @param id
     * @return
     */
    @DeleteMapping("/author/{id}")
    public Response<NullValue> deleteAuthor(@PathVariable long id){
        authorService.deleteAuthor(id);
        return Response.success();
    }

    /**
     * 更新作者
     * @param id
     * @param authorDTO
     * @return
     */
    @PutMapping("/author/{id}")
    public Response<AuthorDTO> updateAuthor(@PathVariable long id, @RequestBody AuthorDTO authorDTO){
        return Response.success(authorService.updateAuthor(id, authorDTO));
    }

    @GetMapping("/author/page")
    public PageResponse<AuthorDTO> pageQuery(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "10") int size){
        return PageResponse.success(authorService.findAllOfPage(page, size));
    }

    @GetMapping("/author/page/with-books")
    public PageResponse<AuthorBookDTO> pageQueryWithOrder(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        return PageResponse.success(authorService.findAllOfPageWithOrder(page, size));
    }

}
