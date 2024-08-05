package com.intuit.commentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Builder
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Data
public class UsersEntity {

    public UsersEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    private String profileUrl;

    private boolean isActive;

    @CreatedDate
    private Date createdAt;
}
