package CS545lab3.miu.edulab3.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // for specific not global
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException
            (ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
        ErrorDetails errorDetails =new ErrorDetails(
                LocalTime.now(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"

        );
        return  new ResponseEntity<>( errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class) // for specific not global
    public ResponseEntity<ErrorDetails> globalException
            (Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails =new ErrorDetails(
                LocalTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"

        );
        return  new ResponseEntity<>( errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors =new HashMap<>();
        List<ObjectError> errorList=  ex.getBindingResult().getAllErrors();
        errorList.forEach((error)->{
            String fieldName =((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNameAExistException.class) // for specific not global
    public ResponseEntity<ErrorDetails> handleUserNameAExistException
            (UserNameAExistException userNameAExistException, WebRequest webRequest){
        ErrorDetails errorDetails =new ErrorDetails(
                LocalTime.now(),
                userNameAExistException.getMessage(),
                webRequest.getDescription(false),
                "USER_NAME_ALREADY_EXISTS"

        );
        return  new ResponseEntity<>( errorDetails, HttpStatus.BAD_REQUEST);

    }
}
