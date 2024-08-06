package com.intuit.commentService.entity;

import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Request.UpdateCommentRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Builder
@Table(name = "comment")
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private PostEntity post;

    private String type;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private CommentEntity parent;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private UsersEntity user;

    private Boolean isEdited = false;

    @Column(nullable = false, length = 1250)
    private String content;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public static CommentEntity mapToCommentEntity(Comment comment, boolean isEdited){
        return CommentEntity.builder()
                .parent(comment.getParentId() != null ?
                        CommentEntity.builder().id(comment.getParentId()).build(): null)
                .content(comment.getContent())
                .user(UsersEntity.builder().id(comment.getUserId()).build())
                .post(PostEntity.builder().id(comment.getPostId()).build())
                .isEdited(isEdited)
                .build();
    }

    public static CommentEntity mapToCommentEntity(CommentEntity commentEntity, UpdateCommentRequest updateCommentRequest){
        if(updateCommentRequest.getContent() != null){
            commentEntity.setContent(updateCommentRequest.getContent());
            commentEntity.setIsEdited(true);
        }
        return commentEntity;
    }
}
