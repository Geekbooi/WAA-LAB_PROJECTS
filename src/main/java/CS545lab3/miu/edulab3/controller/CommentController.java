package CS545lab3.miu.edulab3.controller;

import CS545lab3.miu.edulab3.dtos.CommentDto;
import CS545lab3.miu.edulab3.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/posts/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
   @GetMapping
   @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComment(){

         return    commentService.getAllComment();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable long id){


        return    commentService.getComment(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getCommentById(@PathVariable long id ,@Valid @RequestBody CommentDto commentDto){

        commentService.updateById(id,commentDto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable long id){

        commentService.deleteById(id);
    }
}
