package com.intuit.commentService.design_pattern.strategy.addReaction.implementation;

import com.intuit.commentService.dao.ReactionDao;
import com.intuit.commentService.dao.ReactionMetaDao;
import com.intuit.commentService.dao.UserDao;
import com.intuit.commentService.design_pattern.strategy.addReaction.IAddReactionStrategy;
import com.intuit.commentService.dto.Request.ReactionRequest;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.entity.UsersEntity;
import com.intuit.commentService.service.IReactionCountService;
import com.intuit.commentService.service.IReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddReactionToDb implements IAddReactionStrategy {
    private final IReactionCountService reactionCountService;

    private final ReactionDao reactionDao;

    private final UserDao userDao;

    private final ReactionMetaDao reactionMetaDao;

    private IReactionService reactionService;

    @Autowired
    @Lazy
    public void setProductRepository(IReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @Override
    public void add(ReactionRequest reactionRequest) {
        reactionService.delete(reactionRequest.getEntityType(), reactionRequest.getEntityId(), reactionRequest.getUserId());
        UsersEntity user = userDao.getUserById(reactionRequest.getUserId());
        ReactionMetaEntity reactionMeta = reactionMetaDao.getByReactionMetaId(reactionRequest.getReactionMetaId());
        reactionDao.add(ReactionEntity.mapToReaction(reactionRequest, user, reactionMeta));
        reactionCountService.incrementCount(reactionRequest.getEntityType(), reactionRequest.getEntityId(),
                reactionRequest.getReactionMetaId());
    }
}
