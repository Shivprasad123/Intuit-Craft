package com.intuit.commentService.service.implementation;

import com.intuit.commentService.constants.Constants;
import com.intuit.commentService.dao.CommentDao;
import com.intuit.commentService.design_pattern.strategy.addComment.IAddCommentStrategy;
import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Request.UpdateCommentRequest;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.enums.EntityType;
import com.intuit.commentService.exception.CustomException;
import com.intuit.commentService.service.ICommentService;
import com.intuit.commentService.service.IReactionCountService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentDao commentDao;

    private final IReactionCountService reactionCountService;

    private final IAddCommentStrategy iAddCommentStrategy;

    @Override
    public CommentResponse add(Comment commentRequest){
        return iAddCommentStrategy.add(commentRequest);
    }

    @Override
    public Pair<List<CommentResponse>, PaginationData> getByParentId(String commentId, Integer pageNo, Integer pageSize) {
        Pair<List<CommentEntity>, PaginationData> commentEntities = commentDao.getByParentId(commentId, pageNo, pageSize);
        List<CommentResponse> commentResponses = commentEntities.getKey().stream()
                .map(CommentResponse::mapToCommentResponse)
                .map(commentResponse -> populateReactionCount(commentResponse, EntityType.comment.name(), commentId))
                .collect(Collectors.toList());
        return new ImmutablePair<>(commentResponses, commentEntities.getValue());
    }

    public CommentResponse populateReactionCount(CommentResponse commentResponse,
                                                 String entityType, String entityId){
        commentResponse.setReactionCount(reactionCountService.get("comment", commentResponse.getId()));
        return commentResponse;
    }

    @Override
    public Pair<List<CommentResponse>, PaginationData> getByPostId( String postId, Integer pageNo, Integer pageSize) {
        Pair<List<CommentEntity>, PaginationData> commentEntities =  commentDao.getByPostId(postId, pageNo, pageSize);
        List<CommentResponse> commentResponses = commentEntities.getKey().stream()
                .map(CommentResponse::mapToCommentResponse)
                .map(commentResponse -> populateReactionCount(commentResponse, EntityType.post.name(), postId))
                .collect(Collectors.toList());
        return new ImmutablePair<>(commentResponses, commentEntities.getValue());
    }

    @Override
    public CommentResponse update(String commentId, UpdateCommentRequest updateCommentRequest) {
        Optional<CommentEntity> commentEntity = commentDao.getById(commentId);
        if(commentEntity.isEmpty()){
            throw new CustomException(Constants.ErrorMessages.INVALID_COMMENT_ID,
                    Constants.ErrorCodes.INVALID_COMMENT_ID);
        }

        CommentEntity comment = commentEntity.get();
        CommentEntity.mapToCommentEntity(comment, updateCommentRequest);
        commentDao.add(comment);
        return CommentResponse.mapToCommentResponse(comment);
    }
}
