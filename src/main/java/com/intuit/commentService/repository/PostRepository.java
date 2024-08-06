package com.intuit.commentService.repository;

import com.intuit.commentService.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostRepository extends JpaRepository<PostEntity, String> {
}
