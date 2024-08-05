package com.intuit.commentService.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class ReactionCountId implements Serializable {
    private String entityId;

    private String entityType;

    private ReactionMetaEntity reactionMeta;
}
