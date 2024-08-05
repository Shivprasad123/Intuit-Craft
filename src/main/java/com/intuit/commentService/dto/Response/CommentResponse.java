package com.intuit.commentService.dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.entity.CommentEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse extends Comment {

    private String id;
    private boolean isEdited;
    private Date createdAt;
    private Date updatedAt;
    private List<ReactionCountResponse> reactionCount;
    private Users user;

    public static CommentResponse mapToCommentResponse(CommentEntity commentEntity){
        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId(commentEntity.getId());
        commentResponse.setEdited(commentEntity.getIsEdited());
        commentResponse.setParentId(commentEntity.getParent() != null ?
                commentEntity.getParent().getId() : null);
        commentResponse.setPostId(commentEntity.getPost() != null ?
                commentEntity.getPost().getId() : null);
        commentResponse.setContent(commentEntity.getContent());
        commentResponse.setCreatedAt(commentEntity.getCreatedAt());
        commentResponse.setUpdatedAt(commentEntity.getUpdatedAt());
        commentResponse.setUser(Users.mapToUser(commentEntity.getUser()));

        return commentResponse;
    }
}
