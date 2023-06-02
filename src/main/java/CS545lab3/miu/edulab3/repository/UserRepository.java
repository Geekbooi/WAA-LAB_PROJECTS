package CS545lab3.miu.edulab3.repository;

import CS545lab3.miu.edulab3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   // User findByName(String name);
    @Query("SELECT  u FROM User u where u.name =?1 ")
   Optional<User> UserByName(String name);
    
    @Query("SELECT u FROM User u where size (u.posts) > 1")


   List<User> findUsersWithMoreThanOnePost();


  @Query("SELECT u FROM User u where size(u.posts)>:number")


  List<User> usersMoreThanSpecifiedPosts(@Param("number") int number);
    @Query("SELECT u FROM User u join  u.posts p where p.title =?1")
    List<User> getUserByPostTitle(String title);

}

