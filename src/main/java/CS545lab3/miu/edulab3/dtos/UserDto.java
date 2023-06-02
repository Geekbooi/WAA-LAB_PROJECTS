package CS545lab3.miu.edulab3.dtos;

import CS545lab3.miu.edulab3.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class UserDto {
    private Long  id;
    @NotBlank(message = "user name can not be blank")
    private  String name;

    private List<Post> posts;
}
