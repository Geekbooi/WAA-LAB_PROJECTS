package CS545lab3.miu.edulab3.repository;

import CS545lab3.miu.edulab3.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
