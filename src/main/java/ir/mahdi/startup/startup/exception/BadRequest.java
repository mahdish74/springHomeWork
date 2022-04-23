package ir.mahdi.startup.startup.exception;

import org.springframework.http.HttpStatus;

public class BadRequest extends ApplicationException {
    public BadRequest(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
