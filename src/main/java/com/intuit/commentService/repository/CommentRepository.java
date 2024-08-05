package com.intuit.commentService.repository;

import com.intuit.commentService.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String>,
        PagingAndSortingRepository<CommentEntity, String> {

    Optional<CommentEntity> findById(String id);
    Optional<List<CommentEntity>> findByPostIdAndParentId(String postId, String parentId);
    Page<CommentEntity> findByPostIdAndParentId(String postId, String parentId, Pageable pageable);
    Optional<List<CommentEntity>> findByParentId(String parentId);
    Page<CommentEntity> findByParentId(String parentId, Pageable pageable);
}
