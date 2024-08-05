package com.intuit.commentService.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReactionResponse {
    private String entityType;
    private String entityId;
    private Long reactionMetaId;
    private String reactionType;
    private List<ReactedUsers> users;
}
