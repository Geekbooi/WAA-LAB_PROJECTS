package CS545lab3.miu.edulab3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ErrorDetails {
    private LocalTime timestamp;
    private String message;
    private String path;
    private String errorCode;
}
