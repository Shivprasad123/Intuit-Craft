package com.intuit.commentService.service.implementation;

import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.service.ICommentService;
import com.intuit.commentService.service.IPostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final ICommentService commentService;
    public Pair<List<CommentResponse>, PaginationData> getComments(String postId, Integer pageNo, Integer pageSize){
        return commentService.getByPostId(postId, pageNo, pageSize);
    }
}
