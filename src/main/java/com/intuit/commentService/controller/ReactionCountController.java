package com.intuit.commentService.controller;

import com.intuit.commentService.dto.Response.CSResponse;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.ReactionCountResponse;
import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.enums.ResponseStatus;
import com.intuit.commentService.service.IReactionCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reactionCount")
@RequiredArgsConstructor
public class ReactionCountController {

    private final IReactionCountService reactionCountService;

    @GetMapping
    private CSResponse<List<ReactionCountResponse>> get(@RequestParam(name = "entityType") String entityType,
                                                      @RequestParam(name = "entityId") String entityId){
        List<ReactionCountResponse> reactionCountResponses =
                reactionCountService.get(entityType, entityId);
        CSResponse<List<ReactionCountResponse>> response = new CSResponse<>();
        response.setData(reactionCountResponses);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
