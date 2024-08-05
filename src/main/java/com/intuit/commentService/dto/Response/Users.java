package com.intuit.commentService.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.entity.UsersEntity;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
    private String userName;
    private String id;
    private String profileUrl;
    private Date createdAt;

    public static Users mapToUser(UsersEntity usersEntity){
        Users user = new Users();

        if(usersEntity == null) return user;

        user.setUserName(usersEntity.getUserName());
        user.setId(usersEntity.getId());
        user.setProfileUrl(usersEntity.getProfileUrl());
        user.setCreatedAt(usersEntity.getCreatedAt());
        return  user;
    }
}

