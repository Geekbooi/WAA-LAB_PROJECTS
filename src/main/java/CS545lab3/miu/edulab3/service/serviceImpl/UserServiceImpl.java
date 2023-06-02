package CS545lab3.miu.edulab3.service.serviceImpl;

import CS545lab3.miu.edulab3.domain.Post;
import CS545lab3.miu.edulab3.domain.User;
import CS545lab3.miu.edulab3.dtos.PostDto;
import CS545lab3.miu.edulab3.dtos.UserDto;
import CS545lab3.miu.edulab3.exception.ResourceNotFoundException;
import CS545lab3.miu.edulab3.exception.UserNameAExistException;
import CS545lab3.miu.edulab3.repository.PostRepository;
import CS545lab3.miu.edulab3.repository.UserRepository;
import CS545lab3.miu.edulab3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void save(UserDto userDto) throws UserNameAExistException {
        String name = userDto.getName();
       Optional<User> user= userRepository.UserByName(name);

        if(user.isPresent()){
            throw  new UserNameAExistException("user name already exist");
        }
        userRepository.save(modelMapper.map(userDto, User.class));

    }

    @Override
    public UserDto findById(long id) {
        User user  = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));

   return modelMapper.map(user, UserDto.class);

    }

    @Override
    public List<UserDto> findAll() {
        return   userRepository.findAll().stream().map(x->modelMapper.map(x, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {

      userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));

        userRepository.deleteById(id);

    }

    @Override
    public void createPost(long id, PostDto postDto) {

        User user  = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));

            Post post = modelMapper.map(postDto, Post.class);
           //postRepository.save(post);
        user.add(post);
           userRepository.save(user);
    }

    @Override
    public UserDto updateById(long id, UserDto userDto) {
        User existuser  = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));

        User user =modelMapper.map(userDto, User.class);
        user.setId(id);
       User updatedUser = userRepository.save(user);



        return modelMapper.map(updatedUser, UserDto.class);
    }

//    @Override
//    public List<UserDto> getPostByAuthor(String author) {
//        return null;
//    }

    @Override
    public List<PostDto> getAllPostsByUserId(long id)  {

      userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));

        // String author = user.getName();
         List<Post> postList =postRepository.allPostsByAuthorId(id);
         List<PostDto> postDtoList = postList.stream().map(x->modelMapper.map(x, PostDto.class)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public List<UserDto>  manyPostsUsers() {
       List<User> userList  = userRepository.findUsersWithMoreThanOnePost();
      //  System.out.println(userList);
       List< UserDto > userDtoList =new ArrayList<>();
       UserDto userDto =new UserDto();

       for(User user:userList){
           userDto.setId(user.getId());
           userDto.setName(user.getName());
           userDtoList.add(userDto);

       }
        return  userDtoList;
    }

    @Override
    public List<UserDto> usersMoreThanSpecifiedPosts(int number) {
     List<User>  userList=   userRepository.usersMoreThanSpecifiedPosts(number);
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUserByPostTitle(String title) {
        List<User>  userList=   userRepository.getUserByPostTitle(title);
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

}
