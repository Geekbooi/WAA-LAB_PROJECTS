package CS545lab3.miu.edulab3.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PostDto {
    private Long id;
@NotBlank(message = "title can not be blank")
    private   String title;
    @NotBlank(message = "content can not be blank")
    private String content;
    @NotBlank(message = " author can not be blank")
    private  String author;
}
