package backend.Advice;

import backend.Exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice //=ControllerAdvice+ResponseBody. CA注解说明该类全局处理抛出的异常
public class GlobalAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail EntryNotFoundHandler(EntityNotFoundException ex) {
        return new ErrorDetail("Exception", ex.getMessage());
    }
    //ResponseBody注解+Response类的组合可用ResponseEntry类取代
}
