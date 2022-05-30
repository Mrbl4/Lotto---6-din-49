package ro.siit.LottoApp;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExistingUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;
}
