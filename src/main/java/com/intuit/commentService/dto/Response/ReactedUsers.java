package com.intuit.commentService.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.entity.UsersEntity;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReactedUsers extends Users {
    private Date reactedAt;

    public static ReactedUsers mapToReactedUser(ReactionEntity reactionEntity){
        UsersEntity usersEntity = reactionEntity.getUsers();
        ReactedUsers user = new ReactedUsers();

        if(usersEntity == null) return user;

        user.setUserName(usersEntity.getUserName());
        user.setId(usersEntity.getId());
        user.setProfileUrl(usersEntity.getProfileUrl());
        user.setCreatedAt(usersEntity.getCreatedAt());
        user.setReactedAt(reactionEntity.getCreatedAt());
        return  user;
    }
}
