package com.intuit.commentService.repository;

import com.intuit.commentService.entity.ReactionMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "reaction_meta", path = "reaction_meta")
public interface ReactionMetaRepository extends JpaRepository<ReactionMetaEntity, Long> {
}
