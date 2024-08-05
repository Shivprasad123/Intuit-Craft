package com.intuit.commentService.dto.Request;

import com.intuit.commentService.constants.Constants;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateCommentRequest {
    @NotEmpty(message = Constants.ErrorMessages.CONTENT_REQUIRED)
    private String content;
}
