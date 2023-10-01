package ru.spb.rtkdiary.ExceptionControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.spb.rtkdiary.Exception.UserNotFoundException;
import ru.spb.rtkdiary.Response.EntityResponse;

@RestControllerAdvice
@Slf4j
public class RestExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public EntityResponse handle(UserNotFoundException exception){
        log.error(exception.getMessage(),exception);
        return new EntityResponse(exception.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }


}
