package com.intuit.commentService.service.implementation;

import com.intuit.commentService.dao.ReactionCountDao;
import com.intuit.commentService.dao.ReactionMetaDao;
import com.intuit.commentService.dto.Response.ReactionCountResponse;
import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.service.IReactionCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionCountService implements IReactionCountService {

    private final ReactionCountDao reactionCountDao;

    private final ReactionMetaDao reactionMetaDao;


    public List<ReactionCountResponse> get(String entityType, String entityId){

        List<ReactionCountEntity> reactionCountEntities = reactionCountDao
                .get(entityType, entityId);

        List<ReactionCountResponse> reactionCountResponses = reactionCountEntities.stream()
                .map(ReactionCountResponse::mapToListReactionCountResponse).collect(Collectors.toList());

        List<ReactionMetaEntity> reactionMetaEntities = reactionMetaDao.getAll();

        Set<Long> reactionMetaIds = reactionCountResponses.stream()
                .map(ReactionCountResponse::getReactionMetaId).collect(Collectors.toSet());

        /**
         * Adding empty values for not selected reactions
         * */
        for(ReactionMetaEntity reactionMeta: reactionMetaEntities){
            if(!reactionMetaIds.contains(reactionMeta.getId())){
                ReactionCountResponse reactionCountResponse = new ReactionCountResponse();
                reactionCountResponse.setReactionMetaId(reactionMeta.getId());
                reactionCountResponse.setType(reactionMeta.getType());
                reactionCountResponse.setCount(0L);
                reactionCountResponses.add(reactionCountResponse);
            }
        }

        return reactionCountResponses;
    }

    public int decrementCount(String entityType, String entityId, Long reactionMetaId){
        return reactionCountDao.decrementCount(entityType, entityId, reactionMetaId);
    }
    public int incrementCount(String entityType, String entityId, Long reactionMetaId){
        //TODO check if reaction count exist
        int count = reactionCountDao.incrementCount(entityType, entityId, reactionMetaId);

        if(count == 0){
            Optional<ReactionCountEntity> reactionCont = reactionCountDao.get(entityType, entityId, reactionMetaId);
            if(reactionCont.isEmpty()){
                ReactionMetaEntity reactionMeta = reactionMetaDao.getByReactionMetaId(reactionMetaId);
                reactionCountDao.add(entityType, entityId, reactionMeta);
                count = reactionCountDao.incrementCount(entityType, entityId, reactionMetaId);
            }
            if(count == 0){
                //Thorw exception unable to increment
            }
        }
        return count;

    }

}
