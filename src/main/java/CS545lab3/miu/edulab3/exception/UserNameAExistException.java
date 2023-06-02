package CS545lab3.miu.edulab3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNameAExistException  extends  Exception{
    private String message;
    public UserNameAExistException(String message){
        super(message);
    }

}
