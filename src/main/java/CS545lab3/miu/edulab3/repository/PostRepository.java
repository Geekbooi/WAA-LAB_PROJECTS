package CS545lab3.miu.edulab3.repository;

import CS545lab3.miu.edulab3.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
   // List<Post> findByAuthor(String author);
    @Query(value = "SELECT * FROM posts WHERE author_name = :author_name", nativeQuery = true)

    List<Post> allPostsByAuthor( @Param("author_name") String author);
    @Query(value = "SELECT * FROM posts WHERE user_id = :user_id", nativeQuery = true)

    List<Post> allPostsByAuthorId( @Param("user_id") long id);
    @Query("SELECT p from Post p where p.title=:title1 ")
    List<Post> postsWithSpecifiedTitle(@Param("title1") String title);


}
