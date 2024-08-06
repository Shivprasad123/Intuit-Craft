package com.intuit.commentService.service.implementation;

import com.intuit.commentService.dao.ReactionDao;
import com.intuit.commentService.dao.ReactionMetaDao;
import com.intuit.commentService.dao.UserDao;
import com.intuit.commentService.design_pattern.strategy.addReaction.IAddReactionStrategy;
import com.intuit.commentService.dto.Request.ReactionRequest;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.dto.Response.ReactedUsers;
import com.intuit.commentService.dto.Response.ReactionResponse;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.entity.UsersEntity;
import com.intuit.commentService.service.IReactionCountService;
import com.intuit.commentService.service.IReactionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionService implements IReactionService {

    private final IReactionCountService reactionCountService;

    private final ReactionDao reactionDao;


    private final ReactionMetaDao reactionMetaDao;

    private final IAddReactionStrategy addReactionStrategy;

    public Pair<ReactionResponse, PaginationData> get(String entityType, String entityId,
                                Long reactionMetaId, Integer pageNo,Integer pageSize){
        Pair<List<ReactionEntity>, PaginationData>
                reactionEntities = reactionDao.get(entityType, entityId, reactionMetaId, pageNo, pageSize);
        ReactionResponse reactionResponse = new ReactionResponse();
        List<ReactedUsers> users = reactionEntities.getKey().stream()
                .map(ReactedUsers::mapToReactedUser).collect(Collectors.toList());

        reactionResponse.setUsers(users);
        reactionResponse.setReactionMetaId(reactionMetaId);
        reactionResponse.setReactionType(reactionMetaDao.getReactionTypeByMetaId(reactionMetaId));
        reactionResponse.setEntityId(entityId);
        reactionResponse.setEntityType(entityType);
        return new ImmutablePair<>(reactionResponse, reactionEntities.getValue());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void add(ReactionRequest reactionRequest){
        //TODO: need to handle LockAcquisitionException
        addReactionStrategy.add(reactionRequest);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(String entityType, String entityId,
                       String userId){
        Optional<ReactionEntity> reaction = reactionDao.get(entityType, entityId, userId);
        if(reaction.isPresent()){
            int count = reactionDao.delete(entityType, entityId, userId);
            if(count != 0){
                reactionCountService.decrementCount(entityType, entityId, reaction.get().getReactionMeta().getId());
            }
        }
        //TODO: need to handle LockAcquisitionException
    }
}
