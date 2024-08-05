package com.intuit.commentService.design_pattern.strategy.addComment.implementation;

import com.intuit.commentService.dao.CommentDao;

import com.intuit.commentService.design_pattern.strategy.addComment.IAddCommentStrategy;
import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Primary
public class AddCommentToDb implements IAddCommentStrategy {

    private final CommentDao commentDao;

    @Override
    public CommentResponse add(Comment commentRequest) {
        CommentEntity comment = commentDao.add(
                CommentEntity.mapToCommentEntity(commentRequest, false)
        );
        return CommentResponse.mapToCommentResponse(comment);
    }
}
