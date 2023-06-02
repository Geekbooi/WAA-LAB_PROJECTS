package CS545lab3.miu.edulab3.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CommentDto {
    long id;
    @NotBlank(message = "comment can not be blank")
    String name;

}
