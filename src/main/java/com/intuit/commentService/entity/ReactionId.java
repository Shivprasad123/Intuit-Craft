package com.intuit.commentService.entity;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class ReactionId implements Serializable {

    private String entityId;

    private String entityType;

    private UsersEntity users;

    public ReactionId(){}
}
