package com.intuit.commentService.controller;

import com.intuit.commentService.dto.Response.CSResponse;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.enums.ResponseStatus;
import com.intuit.commentService.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;

    @GetMapping("/{postId}/comment")
    private CSResponse<List<CommentResponse>> getComments(
            @PathVariable("postId") String postId,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize){

        Pair<List<CommentResponse>, PaginationData> commentResponse = postService.getComments(postId, pageNo, pageSize);
        CSResponse<List<CommentResponse>> response = new CSResponse<>();
        response.setData(commentResponse.getKey());
        response.setPage(commentResponse.getValue());
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
