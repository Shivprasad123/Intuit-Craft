package com.intuit.commentService.design_pattern.strategy.addReaction;

import com.intuit.commentService.dto.Request.ReactionRequest;

public interface IAddReactionStrategy {
    void add(ReactionRequest reactionRequest);
}
