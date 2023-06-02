package CS545lab3.miu.edulab3.controller;

import CS545lab3.miu.edulab3.dtos.CommentDto;
import CS545lab3.miu.edulab3.dtos.PostDto;
import CS545lab3.miu.edulab3.service.PostService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("api/v1/user/posts")
    public class PostController {
        @Autowired
        public PostService postService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
@PostMapping("/{id}/comments")
@ResponseStatus(HttpStatus.CREATED)
public void createComment (@PathVariable long id,  @RequestBody  @Valid CommentDto commentDto){
    postService.createComment(id,commentDto);

}
        @GetMapping("/{id}")
        public ResponseEntity<PostDto> getById (@PathVariable long id){
            PostDto postDto=postService.findById(id);
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        }
        @GetMapping
        public ResponseEntity<List<PostDto>> getAllPost (){
            List<PostDto> postDtoList =postService.findAll();
            return new ResponseEntity<>(postDtoList, HttpStatus.OK);
        }
        @GetMapping("/author/{author}")
        public ResponseEntity<List<PostDto>> getPostBYAuthorName (@PathVariable("author") String author){
            List<PostDto> postDtoList =postService.getPostByAuthor(author);
            return new ResponseEntity<>(postDtoList, HttpStatus.OK);
        }
//        @PostMapping("")
//        @ResponseStatus(HttpStatus.CREATED)
//        public void createPost(@RequestBody PostDto postDto)throws Exception{
//            postService.save(postDto);
//        }
        @PutMapping ("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public PostDto updatePost(@PathVariable long id, @Valid  @RequestBody PostDto postDto){
            PostDto postDto1=   postService.updateById(id,postDto);

            return postDto1;
        }
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        //@ResponseStatus(HttpStatus.NO_CONTENT)
        public void removePost(@PathVariable long id) {
            postService.deleteById(id);

        }
        @GetMapping(value =
                {
                        "/handlingMultipleEndpoints",
                        "/handlingMultipleEndpoints/author"
                })
        public ResponseEntity<List<PostDto>>  multipleEndpointsDemo(@PathParam(value="author") String author) {
            if (author != null) {
                // return "ID: " + author;
                return  new ResponseEntity<>( postService.getPostByAuthor(author), HttpStatusCode.valueOf(200));
            } else {
                // return "ID missing";
                return null;
            }
        }
        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/title/{title}")
        public List<PostDto> postsWithSpecifiedTitle(@PathVariable  String title){
   return  postService.postsWithSpecifiedTitle(title);

        }

    }


