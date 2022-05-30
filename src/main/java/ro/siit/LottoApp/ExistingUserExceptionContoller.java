package ro.siit.LottoApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExistingUserExceptionContoller {

    @ExceptionHandler(value=ExistingUserException.class)
    public ResponseEntity<Object> exception(ExistingUserException exception){
        return new ResponseEntity<>("There is already one Player with this Name. Click BACK and please try again", HttpStatus.NOT_ACCEPTABLE);
    }
}
