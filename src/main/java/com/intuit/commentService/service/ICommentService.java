package com.intuit.commentService.service;

import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Request.UpdateCommentRequest;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ICommentService {
    CommentResponse add(Comment comment);

    Pair<List<CommentResponse>, PaginationData> getByParentId(String commentId, Integer pageNo, Integer pageSize);
    Pair<List<CommentResponse>, PaginationData> getByPostId(String postId, Integer pageNo, Integer pageSize);

    CommentResponse update(String commentId, UpdateCommentRequest commentRequest);
}
