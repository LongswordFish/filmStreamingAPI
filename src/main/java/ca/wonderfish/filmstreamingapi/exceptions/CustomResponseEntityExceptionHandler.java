package ca.wonderfish.filmstreamingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleProjectIdException(FilmIdException filmIdException, WebRequest webRequest){
        FilmIdExceptionResponse filmIdExceptionResponse = new FilmIdExceptionResponse(filmIdException.getMessage());
        return new ResponseEntity(filmIdExceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
