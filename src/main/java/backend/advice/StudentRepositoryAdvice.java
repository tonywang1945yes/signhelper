package backend.advice;

import backend.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class StudentRepositoryAdvice {
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail EntryNotFoundHandler(EntityNotFoundException ex){
        return new ErrorDetail("EntityNotFoundException", ex.getMessage());
    }

}
