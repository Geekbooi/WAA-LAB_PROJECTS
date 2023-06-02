package CS545lab3.miu.edulab3.service.serviceImpl;

import CS545lab3.miu.edulab3.domain.Comment;
import CS545lab3.miu.edulab3.domain.Post;
import CS545lab3.miu.edulab3.dtos.CommentDto;
import CS545lab3.miu.edulab3.dtos.PostDto;
import CS545lab3.miu.edulab3.exception.ResourceNotFoundException;
import CS545lab3.miu.edulab3.repository.CommentRepository;
import CS545lab3.miu.edulab3.repository.PostRepository;
import CS545lab3.miu.edulab3.repository.UserRepository;
import CS545lab3.miu.edulab3.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
@Autowired
    PostRepository postRepository;
@Autowired
   UserRepository userRepository;

@Autowired
    CommentRepository commentRepository;
   @Autowired
    ModelMapper modelMapper;

/*
    @Override
    public void save(PostDto postDto) throws Exception {
        Post post =   modelMapper.map(postDto, Post.class);
         String author = post.getAuthor();
       // System.out.println(author);
         User user = userRepository.findByName(author);


         if(user ==null){
             throw new Exception("no user has registered with given user name");

         }
        postRepository.save(post);
         user.add(post);
        //System.out.println(user);
         userRepository.save(user);




    }
*/
@Override
public void createComment(long id, CommentDto commentDto) {
    Comment comment = modelMapper.map(commentDto, Comment.class);
   Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
    post.add(comment);
   comment.setPost(post);

   // post.setId(id);
    postRepository.save(post);



}

    @Override
    public PostDto findById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));

        PostDto postDto =modelMapper.map(post, PostDto.class);


        return postDto;

    }

    @Override
    public List<PostDto> findAll() {
        List<Post> postList = postRepository.findAll();
        List<PostDto>postDtoList =postList.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public void deleteById(long id) {
        //System.out.println("here1");
        postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
       // System.out.println("here");
       postRepository.deleteById(id);



    }

    @Override
    public PostDto updateById(long id, PostDto postDto) {
        Post existPost =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));

        Post post = modelMapper.map(postDto, Post.class);
        post.setId(id);
      String authorInDB =  existPost.getAuthor();
      String author = postDto.getAuthor();
      if(!authorInDB.equals(author)){
          post.setAuthor(authorInDB);
      }


        Post updatedPost =  postRepository.save(post);
        PostDto updatedPostDto = modelMapper.map(updatedPost, PostDto.class);
        return updatedPostDto;
    }

    @Override
    public List<PostDto> getPostByAuthor(String author) {
       // List<PostDto> postDtoList = postRepository.getPostByAuthor(author).stream().map(post->modelMapper.map(post, PostDto1.class)).collect(Collectors.toList());
       List<PostDto> postDtoList = postRepository.allPostsByAuthor(author).stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());


        return postDtoList;
    }

    @Override
    public List<PostDto> postsWithSpecifiedTitle(String title) {
        return postRepository.postsWithSpecifiedTitle(title).stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }


}
