package com.intuit.commentService.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentService.enums.ResponseStatus;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CSResponse<T>{
    ResponseStatus status;
    T data;
    ErrorResponse error;
    PaginationData page;
}
