package com.intuit.commentService.service;

import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface IPostService {
    Pair<List<CommentResponse>, PaginationData> getComments(String postId, Integer pageNo, Integer pageSize);
}
