// package com.example.demo.exception;

// import org.springframework.http.*;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestControllerAdvice
// public class ApiExceptionHandler {

//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<?> handleIllegal(IllegalArgumentException ex) {
//         return ResponseEntity
//                 .badRequest()
//                 .body(Map.of("error", ex.getMessage()));
//     }

//     @ExceptionHandler(RuntimeException.class)
//     public ResponseEntity<?> handleRuntime(RuntimeException ex) {
//         return ResponseEntity
//                 .status(HttpStatus.NOT_FOUND)
//                 .body(Map.of("error", ex.getMessage()));
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<?> handleAll(Exception ex) {
//         return ResponseEntity
//                 .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(Map.of("error", ex.getMessage()));
//     }
// }

package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        if (ex.getMessage().toLowerCase().contains("not found")) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


