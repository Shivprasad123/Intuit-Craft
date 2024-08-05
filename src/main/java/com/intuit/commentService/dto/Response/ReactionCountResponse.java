package com.intuit.commentService.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.entity.ReactionEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReactionCountResponse {
    private Long reactionMetaId;
    private String type;
    private Long count;

    public static ReactionCountResponse mapToListReactionCountResponse(ReactionCountEntity reactionCountEntity){
        ReactionCountResponse reactionCountResponse = new ReactionCountResponse();
        reactionCountResponse.setReactionMetaId(reactionCountEntity.getReactionMeta().getId());
        reactionCountResponse.setCount(reactionCountEntity.getCount());
        reactionCountResponse.setType(reactionCountEntity.getReactionMeta().getType());
        return reactionCountResponse;
    }
}
