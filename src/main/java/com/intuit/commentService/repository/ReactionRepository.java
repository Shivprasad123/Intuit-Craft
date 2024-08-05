package com.intuit.commentService.repository;

import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.entity.ReactionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionEntity, ReactionId> {
    Optional<List<ReactionEntity>> findByEntityTypeAndEntityIdAndReactionMetaId(String entityType,
                                                                                String entityId,
                                                                                Long reactionMetaId);

    Page<ReactionEntity> findByEntityTypeAndEntityIdAndReactionMetaId(String entityType,
                                                                      String entityId,
                                                                      Long reactionMetaId,
                                                                      Pageable pagable);

    Optional<ReactionEntity> findByEntityTypeAndEntityIdAndUsersId(String entityType,
                                                                                String entityId,
                                                                                String userId);

    int deleteByEntityTypeAndEntityIdAndUsersId(String entityType, String entityId,
                                                String userId);
}
