package ivan.zelezinski.lab.exceptions;

import ivan.zelezinski.lab.mapper.exception.ExceptionMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessageDto> notFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ExceptionMessageDto(
                exception.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessageDto> badRequestException(BadRequestException exception) {
        return new ResponseEntity<>(new ExceptionMessageDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionMessageDto> badRequest(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        FieldError firstError = fieldErrors.get(0);
        String errorMsg = String.format("Invalid value \"%s\" in %s. %s", firstError.getRejectedValue(),
                firstError.getField(), firstError.getDefaultMessage());
        return new ResponseEntity<>(new ExceptionMessageDto(
                errorMsg, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

}
