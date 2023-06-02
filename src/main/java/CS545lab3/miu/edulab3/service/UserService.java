package CS545lab3.miu.edulab3.service;

import CS545lab3.miu.edulab3.domain.User;
import CS545lab3.miu.edulab3.dtos.PostDto;
import CS545lab3.miu.edulab3.dtos.UserDto;
import CS545lab3.miu.edulab3.exception.UserNameAExistException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
    public void save (UserDto userDto) throws UserNameAExistException;
    public UserDto findById (long id);
    public List<UserDto> findAll();
    public void deleteById(long id);
  public void   createPost( long id,PostDto postDto) throws Exception;
    public UserDto updateById(long id, UserDto userDto);
   // public List<UserDto> getPostByAuthor(String author);
   public List<PostDto>   getAllPostsByUserId(long id) ;
    List<UserDto> manyPostsUsers();
    List<UserDto> usersMoreThanSpecifiedPosts(int number);
    List<UserDto> getUserByPostTitle(String title);


}
