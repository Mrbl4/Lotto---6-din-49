package ro.siit.LottoApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class NumberExceptionController {

    @ExceptionHandler(value = IncorrectNumberException.class)
    public ResponseEntity<Object> exception(IncorrectNumberException exception) {
        return new ResponseEntity<>("Incorrect number. Press enter to retry", HttpStatus.CONTINUE);
            }
}