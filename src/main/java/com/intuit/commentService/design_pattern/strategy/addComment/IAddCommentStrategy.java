package com.intuit.commentService.design_pattern.strategy.addComment;

import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Response.CommentResponse;

public interface IAddCommentStrategy {
    CommentResponse add(Comment commentRequest);
}
