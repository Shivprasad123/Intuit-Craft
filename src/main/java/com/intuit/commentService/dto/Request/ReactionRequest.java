package com.intuit.commentService.dto.Request;

import com.intuit.commentService.constants.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReactionRequest {
    @NotEmpty(message = Constants.ErrorMessages.ENTITY_ID_REQUIRED)
    private String entityId;

    @NotEmpty(message = Constants.ErrorMessages.ENTITY_TYPE_REQUIRED)
    private String entityType;

    @NotEmpty(message = Constants.ErrorMessages.USER_ID_REQUIRED)
    private String userId;

    @NotNull(message = Constants.ErrorMessages.REACTION_META_ID_REQUIRED)
    private Long reactionMetaId;
}
