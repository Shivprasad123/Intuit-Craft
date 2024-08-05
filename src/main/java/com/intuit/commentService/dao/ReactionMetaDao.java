package com.intuit.commentService.dao;

import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.repository.ReactionMetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReactionMetaDao {
    private final ReactionMetaRepository reactionMetaRepository;

    public ReactionMetaEntity getByReactionMetaId(Long id){
        return reactionMetaRepository.getById(id);
    }

    public String getReactionTypeByMetaId(Long id){
        ReactionMetaEntity reactionMeta = reactionMetaRepository.getById(id);
        if(reactionMeta == null) return null;
        return reactionMeta.getType();
    }

    public List<ReactionMetaEntity> getAll(){
        return reactionMetaRepository.findAll();
    }
}
