package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.Artist.ArtistServiceError;
import service.ArtistRecordHouse.ArtistRecordHouseRelationServiceError;

@ControllerAdvice
public class ArtistRecordHouseControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArtistRecordHouseRelationServiceError.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            ArtistServiceError error) {

        return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
