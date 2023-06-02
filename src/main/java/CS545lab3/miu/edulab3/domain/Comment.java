package CS545lab3.miu.edulab3.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
  private   String name;
  @ManyToOne()
  @JoinColumn(name= "post_id")
  @JsonBackReference
  private Post post;


}
