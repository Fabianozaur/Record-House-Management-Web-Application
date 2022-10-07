package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.Playlist.PlaylistServiceError;

@ControllerAdvice
public class PlaylistControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlaylistServiceError.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            PlaylistServiceError error) {

        return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
