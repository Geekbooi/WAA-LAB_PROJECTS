package CS545lab3.miu.edulab3.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private   String title;
    private String content;
    @Column(name = "author_name")
    private  String author;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    @BatchSize(size = 5)
    @JsonBackReference
    private List<Comment> commentList=new ArrayList<>();
    public void add (Comment comment){
        commentList.add(comment);
    }

}
