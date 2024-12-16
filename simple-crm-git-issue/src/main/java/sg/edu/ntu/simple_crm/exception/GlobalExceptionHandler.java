package sg.edu.ntu.simple_crm.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Add exception handlers here

    // @ExceptionHandler(CustomerNotFoundException.class)
    // public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
    //     // return new ResponseEntity<>("🚨 " + e.getMessage(), HttpStatus.NOT_FOUND);
    //     ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    // }

    // @ExceptionHandler(InteractionNotFoundException.class)
    // public ResponseEntity<ErrorResponse> handleInteractionNotFoundException(InteractionNotFoundException e) {
    //     ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
    //     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    // }

    @ExceptionHandler({ CustomerNotFoundException.class, InteractionNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    };

    // Validation exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
    // Get a list of validation errors
    List<ObjectError> validationErrors = e.getBindingResult().getAllErrors();

    // Create a StringBuilder to store all messages
    StringBuilder sb = new StringBuilder();

    // Loop through all the errors and append the messages
    for (ObjectError error : validationErrors) {
      sb.append(error.getDefaultMessage() + ". ");
    }

    ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


    // General Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),
        // LocalDateTime.now());
        // logger.error....
        ErrorResponse errorResponse = new ErrorResponse("Something went wrong. Please contact support.",
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

// With GlobalExceptionHandler all exception-handling logic is in one place,
// making it easier to manage and modify exceptions
// Controller then becomes free of try catch blocks

// A general exception handler can catch any exception (both checked and
// unchecked) that is not explicitly handled elsewhere

// Java.util.Optional
// Why Optional is Useful?
// 1. NullPointerException: no need to explicitly check for null values
// 2. Clean code: Avoids cluttered null-checks in your methods