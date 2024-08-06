package com.intuit.commentService.entity;

import com.intuit.commentService.dto.Request.ReactionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@IdClass(ReactionId.class)
@Data
@Builder
@Table(name = "reaction")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class ReactionEntity {

    @Id
    @Column(nullable = false)
    private String entityId;

    @Id
    @Column(nullable = false)
    private String entityType;

    @Id
    @ManyToOne
    @JoinColumn
    private UsersEntity users;

    @ManyToOne
    @JoinColumn
    private ReactionMetaEntity reactionMeta;

    @CreatedDate
    private Date createdAt;

    public static ReactionEntity mapToReaction(ReactionRequest reactionRequest, UsersEntity user,
                                               ReactionMetaEntity reactionMeta){
        return ReactionEntity.builder()
                .entityType(reactionRequest.getEntityType())
                .entityId(reactionRequest.getEntityId())
                .users(user)
                .reactionMeta(reactionMeta)
                .build();
    }
}
