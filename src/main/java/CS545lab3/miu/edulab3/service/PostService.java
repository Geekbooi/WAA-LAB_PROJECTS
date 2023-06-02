package CS545lab3.miu.edulab3.service;

import CS545lab3.miu.edulab3.domain.Post;
import CS545lab3.miu.edulab3.dtos.CommentDto;
import CS545lab3.miu.edulab3.dtos.PostDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostService {
//    public void save (PostDto postDto) throws Exception;
public void   createComment(long id, CommentDto commentDto);
    public PostDto findById (long id);
    public List<PostDto> findAll();
    public void deleteById(long id);
    public PostDto updateById(long id, PostDto postDto);
    public List<PostDto> getPostByAuthor(String author);
    List<PostDto> postsWithSpecifiedTitle( String title);


}
