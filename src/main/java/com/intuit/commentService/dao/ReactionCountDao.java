package com.intuit.commentService.dao;

import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.repository.ReactionCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionCountDao {
    private final ReactionCountRepository reactionCountRepository;


    public List<ReactionCountEntity> get(String entityType, String entityId){
        Optional<List<ReactionCountEntity>> optionalReactionCounts =
                reactionCountRepository.findByEntityTypeAndEntityIdOrderByCountDesc(entityType, entityId);
        return optionalReactionCounts.orElseGet(ArrayList::new);
    }

    public Optional<ReactionCountEntity> get(String entityType, String entityId, Long reactionMetaId){
        return reactionCountRepository
                .findByEntityTypeAndEntityIdAndReactionMetaId(entityType, entityId, reactionMetaId);
    }

    public ReactionCountEntity add(String entityType, String entityId, ReactionMetaEntity reactionMeta) {
        return reactionCountRepository.save(ReactionCountEntity.builder()
                .entityId(entityId)
                .entityType(entityType)
                .reactionMeta(reactionMeta)
                .count(0L)
                .build());
    }

    public int incrementCount(String entityType, String entityId, Long reactionMetaId){
        return reactionCountRepository.incrementReactionCount(entityType, entityId, reactionMetaId);
    }

    public int decrementCount(String entityType, String entityId, Long reactionMetaId){
        return reactionCountRepository.decrementReactionCount(entityType, entityId, reactionMetaId);
    }
}
