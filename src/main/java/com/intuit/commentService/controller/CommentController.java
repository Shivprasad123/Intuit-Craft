package com.intuit.commentService.controller;

import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Request.UpdateCommentRequest;
import com.intuit.commentService.dto.Response.CSResponse;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.enums.ResponseStatus;
import com.intuit.commentService.service.ICommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService iCommentService;

    @PostMapping
    private CSResponse<CommentResponse> add(@RequestBody @Valid Comment comment){
        CommentResponse commentResponse = iCommentService.add(comment);
        CSResponse<CommentResponse> response = new CSResponse<>();
        response.setData(commentResponse);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/{commentId}/replies")
    private CSResponse<List<CommentResponse>> get(
            @PathVariable("commentId") String commentId,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize){
        Pair<List<CommentResponse>, PaginationData> commentResponses = iCommentService.getByParentId(commentId, pageNo, pageSize);
        CSResponse<List<CommentResponse>> response = new CSResponse<>();
        response.setData(commentResponses.getKey());
        response.setPage(commentResponses.getValue());
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }


    @PatchMapping("/{commentId}")
    private CSResponse<CommentResponse> update(@PathVariable("commentId") String commentId,
                                               @RequestBody @Valid UpdateCommentRequest updateCommentRequest){
        CommentResponse commentResponse = iCommentService.update(commentId, updateCommentRequest);
        CSResponse<CommentResponse> response = new CSResponse<>();
        response.setData(commentResponse);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    //TODO: Delete comment
}
