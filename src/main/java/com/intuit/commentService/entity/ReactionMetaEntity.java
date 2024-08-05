package com.intuit.commentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Table(name = "reaction_meta")
@AllArgsConstructor
@Data
public class ReactionMetaEntity {

    public ReactionMetaEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
}
