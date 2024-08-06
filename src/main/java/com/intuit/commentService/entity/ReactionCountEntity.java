package com.intuit.commentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@IdClass(ReactionCountId.class)
@Builder
@Table(name = "reaction_count")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReactionCountEntity {

    @Id
    @Column(nullable = false)
    private String entityId;

    @Id
    @Column(nullable = false)
    private String entityType;

    @Column
    @ColumnDefault("0")
    private Long count;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private ReactionMetaEntity reactionMeta;
}
