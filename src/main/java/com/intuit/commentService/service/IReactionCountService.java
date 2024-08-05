package com.intuit.commentService.service;

import com.intuit.commentService.dto.Response.ReactionCountResponse;
import com.intuit.commentService.entity.ReactionCountEntity;

import java.util.List;

public interface IReactionCountService {
    List<ReactionCountResponse> get(String entityType, String entityId);
    int incrementCount(String entityType, String entityId, Long reactionMetaId);
    int decrementCount(String entityType, String entityId, Long reactionMetaId);
}
