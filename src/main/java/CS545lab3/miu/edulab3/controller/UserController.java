package CS545lab3.miu.edulab3.controller;

import CS545lab3.miu.edulab3.dtos.PostDto;
import CS545lab3.miu.edulab3.dtos.UserDto;
import CS545lab3.miu.edulab3.exception.UserNameAExistException;
import CS545lab3.miu.edulab3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser( ){
     List<UserDto> userDtoList=   userService.findAll();
    return new ResponseEntity<>(userDtoList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id ){
      UserDto userDtoList=   userService.findById(id);
        return new ResponseEntity<>(userDtoList, HttpStatusCode.valueOf(200));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createUser(@Valid @RequestBody UserDto userDto ) throws UserNameAExistException {
      userService.save(userDto);

    }
    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public void createPost(@PathVariable long id,   @RequestBody @Valid PostDto postDto ) throws Exception {
       userService.createPost( id, postDto);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @Valid @RequestBody UserDto userDto ){
        userService.updateById(id,userDto);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id ){
        userService.deleteById(id);

    }
    //  // get all posts done by user id
    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable(value = "userId")    long id) {
        List<PostDto> postDtoList=   userService.getAllPostsByUserId(id);
        return new ResponseEntity<>(postDtoList, HttpStatusCode.valueOf(200));
    }
    // get user those have more than 1 posts
    @GetMapping("/posts")
    
    public ResponseEntity<List<UserDto>> manyPostUser() { // need to fix
        List<UserDto> userDtoList=   userService.manyPostsUsers();
        return new ResponseEntity<>(userDtoList, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/number/{number}")
    public ResponseEntity<List<UserDto>> usersMoreThanSpecifiedPosts(@PathVariable int number) {
        List<UserDto> userDtoList=   userService.usersMoreThanSpecifiedPosts(number);
        return new ResponseEntity<>(userDtoList, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<UserDto>> getUserByPostTitle(@PathVariable String title){
        List<UserDto> userDtoList=   userService.getUserByPostTitle(title);
        return new ResponseEntity<>(userDtoList, HttpStatusCode.valueOf(200));
    }
}
