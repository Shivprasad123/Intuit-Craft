package com.intuit.commentService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Builder
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 1250)
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private UsersEntity users;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
