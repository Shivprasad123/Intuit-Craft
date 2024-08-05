package com.intuit.commentService.exception;

import com.intuit.commentService.constants.Constants;
import com.intuit.commentService.dto.Response.CSResponse;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.Error;
import com.intuit.commentService.dto.Response.ErrorResponse;
import com.intuit.commentService.enums.ResponseStatus;
import org.hibernate.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(TypeMismatchException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public CSResponse<Object> handleTypeMismatchException(TypeMismatchException ex) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(ex.getMessage(), Constants.ErrorCodes.BAD_GATEWAY));

        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();
        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_GATEWAY)
    public CSResponse<Object>  handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(ex.getMessage(),Constants.ErrorCodes.BAD_GATEWAY));

        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();
        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public CSResponse<Object>  handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<Error> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> {
                    if(x.getRejectedValue() != null) {
                        return new Error((x.getField() + " : " + x.getDefaultMessage() + " : "+x.getRejectedValue()),Constants.ErrorCodes.INVALID_DATA);
                    } else {
                        return new Error((x.getField() + " : " + x.getDefaultMessage()),Constants.ErrorCodes.INVALID_DATA);
                    }
                })
                .collect(Collectors.toList());

        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }
    @ExceptionHandler(CustomException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public CSResponse<Object> handleCustomException(CustomException ex){

        List<Error> errors = new ArrayList<>();
        errors.add(ex.error);

        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public CSResponse<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error(Constants.ErrorMessages.DATA_INTEGRITY_VIOLATED,
                Constants.ErrorCodes.DATA_INTEGRITY_VIOLATED));
        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public CSResponse<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        List<Error> errors = new ArrayList<>();
        errors.add(
                new Error(
                        ((MethodArgumentTypeMismatchException) ex).getName() +" : "+ Constants.DefaultValues.INVALID_VALUE +" : "+ ex.getValue(),
                Constants.ErrorCodes.INVALID_DATA)
            );
        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }


    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CSResponse<Object> handleException(Exception ex){

        List<Error> errors = new ArrayList<>();
        errors.add(new Error(ex.getMessage(),Constants.ErrorCodes.INTERNAL_SERVER_ERROR_CODE));

        ErrorResponse errorResponse  = ErrorResponse.builder()
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        CSResponse<Object> response = new CSResponse<>();
        response.setError(errorResponse);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }
}
