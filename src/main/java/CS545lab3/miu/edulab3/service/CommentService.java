package CS545lab3.miu.edulab3.service;

import CS545lab3.miu.edulab3.dtos.CommentDto;

import java.util.List;

public interface CommentService {
  public List<CommentDto> getAllComment();
  public CommentDto getComment(long id);
  public void updateById(long id, CommentDto commentDto);
  public  void  deleteById(long id);
}
