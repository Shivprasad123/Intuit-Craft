package com.intuit.commentService.dao;

import com.intuit.commentService.constants.Constants;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionDao {
    private final ReactionRepository reactionRepository;

    public Pair<List<ReactionEntity>, PaginationData> get(String entityType, String entityId,
                                                         Long reactionMetaId, Integer pageNo, Integer pageSize){

        if(pageNo == null){
            pageNo = Constants.DefaultValues.PAGE_NO;
        }
        if(pageSize == null){
            pageSize = Constants.DefaultValues.PAGE_SIZE;
        }
        Pageable pagable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());

        Page<ReactionEntity> reactionPage = reactionRepository.findByEntityTypeAndEntityIdAndReactionMetaId(
                entityType, entityId, reactionMetaId, pagable);


        PaginationData paginationData = PaginationData.builder()
                .total(reactionPage.getTotalElements())
                .pageNo(reactionPage.getNumber())
                .pageSize(reactionPage.getSize())
                .build();

        List<ReactionEntity> reactionEntities = reactionPage.getContent() != null ? reactionPage.getContent():  new ArrayList<>();

        return new ImmutablePair<>(reactionEntities, paginationData);
    }

    public Optional<ReactionEntity> get(String entityType, String entityId, String userId){

        //TODO: Check how to fetch directly using composite key
        return reactionRepository.findByEntityTypeAndEntityIdAndUsersId(
                entityType, entityId, userId);
    }

    public int delete(String entityType, String entityId,
                       String userId){
        return reactionRepository.deleteByEntityTypeAndEntityIdAndUsersId(entityType, entityId, userId);

    }
    public void add(ReactionEntity reactionEntity){
        reactionRepository.save(reactionEntity);
    }
}
