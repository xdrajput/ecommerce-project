package com.mohit.ecommerce.exception;
import org.springframework.http.*; import org.springframework.security.access.AccessDeniedException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler{
 @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ApiError> notFound(ResourceNotFoundException e){return build(HttpStatus.NOT_FOUND,e.getMessage(),Map.of());}
 @ExceptionHandler(BusinessException.class) ResponseEntity<ApiError> business(BusinessException e){return build(HttpStatus.BAD_REQUEST,e.getMessage(),Map.of());}
 @ExceptionHandler(AccessDeniedException.class) ResponseEntity<ApiError> denied(AccessDeniedException e){return build(HttpStatus.FORBIDDEN,"Access denied",Map.of());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ApiError> validation(MethodArgumentNotValidException e){Map<String,String> m=new LinkedHashMap<>(); e.getBindingResult().getFieldErrors().forEach(x->m.put(x.getField(),x.getDefaultMessage())); return build(HttpStatus.BAD_REQUEST,"Validation failed",m);}
 @ExceptionHandler(Exception.class) ResponseEntity<ApiError> other(Exception e){return build(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected server error",Map.of());}
 private ResponseEntity<ApiError> build(HttpStatus s,String msg,Map<String,String> errors){return ResponseEntity.status(s).body(new ApiError(LocalDateTime.now(),s.value(),msg,errors));}
}
