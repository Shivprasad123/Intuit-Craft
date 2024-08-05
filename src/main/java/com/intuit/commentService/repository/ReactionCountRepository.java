package com.intuit.commentService.repository;

import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.entity.ReactionCountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionCountRepository extends JpaRepository<ReactionCountEntity, ReactionCountId> {

    //TODO: Check get by Direct composite key
    Optional<ReactionCountEntity> findByEntityTypeAndEntityIdAndReactionMetaId(String entityType,
                                                                               String entityId,
                                                                               Long reactionMetaId);
    Optional<List<ReactionCountEntity>> findByEntityTypeAndEntityId(String entityType, String entityId);


    @Modifying
    @Query("update ReactionCountEntity rc set rc.count = rc.count+1 " +
            "where rc.entityId = :entityId and rc.entityType = :entityType " +
            "and rc.reactionMeta.id = :reactionMetaId")
    int incrementReactionCount(@Param("entityType") String entityType, @Param("entityId") String entityId,
                            @Param("reactionMetaId") Long reactionMetaId);

    @Modifying
    @Query("update ReactionCountEntity rc set rc.count = rc.count-1 " +
            "where rc.entityId = :entityId and rc.entityType = :entityType " +
            "and rc.reactionMeta.id = :reactionMetaId")
    int decrementReactionCount(@Param("entityType") String entityType, @Param("entityId") String entityId,
                            @Param("reactionMetaId") Long reactionMetaId);
}
