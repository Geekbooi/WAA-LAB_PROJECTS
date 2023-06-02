package CS545lab3.miu.edulab3.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long  id;
  private  String name;
@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE})

@JoinColumn(name = "user_id")
  private List<Post> posts = new ArrayList<>() ;
public void add (Post post){
    posts.add(post);
}
}
