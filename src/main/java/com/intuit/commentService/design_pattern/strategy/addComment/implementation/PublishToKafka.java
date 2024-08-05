package com.intuit.commentService.design_pattern.strategy.addComment.implementation;

import com.intuit.commentService.design_pattern.strategy.addComment.IAddCommentStrategy;
import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Response.CommentResponse;

public class PublishToKafka implements IAddCommentStrategy {
    @Override
    public CommentResponse add(Comment commentRequest) {
        return null;
    }
}
