package com.intuit.commentService.dto.Response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private List<Error> errors;
    private LocalDateTime timeStamp;

}

