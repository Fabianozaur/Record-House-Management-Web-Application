package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.Song.SongServiceError;
import service.SongArtist.SongArtistRelationServiceError;

@ControllerAdvice
public class SongArtistControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SongArtistRelationServiceError.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            SongServiceError error) {

        return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
