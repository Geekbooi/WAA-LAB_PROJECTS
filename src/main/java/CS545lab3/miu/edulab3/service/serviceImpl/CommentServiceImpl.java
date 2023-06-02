package CS545lab3.miu.edulab3.service.serviceImpl;

import CS545lab3.miu.edulab3.domain.Comment;
import CS545lab3.miu.edulab3.dtos.CommentDto;
import CS545lab3.miu.edulab3.exception.ResourceNotFoundException;
import CS545lab3.miu.edulab3.repository.CommentRepository;
import CS545lab3.miu.edulab3.repository.PostRepository;
import CS545lab3.miu.edulab3.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
  ModelMapper modelMapper;

    @Override
    public List<CommentDto> getAllComment() {

        return commentRepository.findAll().stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getComment(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","id",id));

        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public void updateById(long id, CommentDto commentDto) {
        commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","id",id));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setId(id);
       commentRepository.save(comment);

    }

    @Override
    public void deleteById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","id",id));
        commentRepository.deleteById(id);

    }
}

