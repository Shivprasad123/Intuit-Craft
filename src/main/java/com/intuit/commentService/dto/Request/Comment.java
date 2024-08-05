package com.intuit.commentService.dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentService.constants.Constants;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    @NotEmpty(message = Constants.ErrorMessages.POST_ID_REQUIRED)
    private String postId;

    /**
     * If patent id is null then that comment is considered as post comment.
     * */
    private String parentId;

    @NotEmpty(message = Constants.ErrorMessages.CONTENT_REQUIRED)
    private String content;

    @NotEmpty(message = Constants.ErrorMessages.USER_ID_REQUIRED)
    private String userId;
}
