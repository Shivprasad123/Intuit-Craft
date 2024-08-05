package com.intuit.commentService.service;

import com.intuit.commentService.dto.Request.ReactionRequest;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.dto.Response.ReactionResponse;
import com.intuit.commentService.entity.ReactionEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface IReactionService {
    Pair<ReactionResponse, PaginationData> get(String entityType, String entityId, Long reactionMetaId,
                                               Integer pageNo, Integer pageSize);
    void add(ReactionRequest reactionRequest);
    void delete(String entityType, String entityId,
                       String userId);
}
