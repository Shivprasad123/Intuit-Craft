package com.intuit.commentService.controller;

import com.intuit.commentService.dto.Request.ReactionRequest;
import com.intuit.commentService.dto.Response.CSResponse;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.dto.Response.ReactionResponse;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.service.IReactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reaction")
@RequiredArgsConstructor
public class ReactionController {

    private final IReactionService reactionService;

    @GetMapping("/{reactionMetaId}/users")
    private CSResponse<ReactionResponse> get(@RequestParam(name = "entityType") String entityType,
                                        @RequestParam(name = "entityId") String entityId,
                                        @PathVariable("reactionMetaId") Long reactionMetaId,
                                        @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize){
        Pair<ReactionResponse, PaginationData> reactionResponses =  reactionService.get(entityType, entityId, reactionMetaId, pageNo, pageSize);
        CSResponse<ReactionResponse> response = new CSResponse<>();
        response.setData(reactionResponses.getKey());
        response.setPage(reactionResponses.getValue());
        response.setStatus(com.intuit.commentService.enums.ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private CSResponse<Void> add(@RequestBody @Valid ReactionRequest reactionRequest){

        reactionService.add(reactionRequest);
        CSResponse<Void> response = new CSResponse<>();
        response.setStatus(com.intuit.commentService.enums.ResponseStatus.SUCCESS);
        return response;
    }

    //TODO: Check URL
    @DeleteMapping("/{entityType}/{entityId}/user/{userId}")
    private CSResponse<Void> delete(@PathVariable("entityType") String entityType,
                                   @PathVariable("entityId") String entityId,
                                   @PathVariable("userId") String userId){
        reactionService.delete(entityType, entityId, userId);
        CSResponse<Void> response = new CSResponse<>();
        response.setStatus(com.intuit.commentService.enums.ResponseStatus.SUCCESS);
        return response;
    }
}
