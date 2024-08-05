package com.intuit.commentService.exception;

import com.intuit.commentService.dto.Response.Error;

public class CustomException extends RuntimeException{
    Error error;
    public CustomException(String message, String errorCode){
        this.error = new Error(message, errorCode);
    }
    public CustomException(Error error){
        this.error = error;
    }
}

