package ca.wonderfish.filmstreamingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmIdException extends RuntimeException{
    public FilmIdException(String message) {
        super(message);
    }
}
