package com.intuit.commentService.dao;

import com.intuit.commentService.constants.Constants;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final CommentRepository commentRepository;

    public CommentEntity add(CommentEntity commentEntity){
        return commentRepository.save(commentEntity);
    }

    public Optional<CommentEntity> getById(String commentId){
        return commentRepository.findById(commentId);
    }

    public Pair<List<CommentEntity>, PaginationData> getByParentId(String commentId, Integer pageNo, Integer pageSize){
        /**
         * Configured default values for page size and page number
         * */
        if(pageNo == null){
            pageNo = Constants.DefaultValues.PAGE_NO;
        }
        if(pageSize == null){
            pageSize = Constants.DefaultValues.PAGE_SIZE;
        }
        Pageable pagable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());

        Page<CommentEntity> commentEntityPage = commentRepository.findByParentId(commentId, pagable);

        PaginationData paginationData = PaginationData.builder()
                .total(commentEntityPage.getTotalElements())
                .pageNo(commentEntityPage.getNumber())
                .pageSize(commentEntityPage.getSize())
                .build();

        List<CommentEntity> commentEntities = commentEntityPage.getContent();

        return new ImmutablePair<>(commentEntities, paginationData);
    }

    public Pair<List<CommentEntity>, PaginationData> getByPostId(String postId, Integer pageNo, Integer pageSize){

        /**
         * Configured default values for page size and page number
         * */
        if(pageNo == null){
            pageNo = Constants.DefaultValues.PAGE_NO;
        }
        if(pageSize == null){
            pageSize = Constants.DefaultValues.PAGE_SIZE;
        }
        Pageable pagable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());

        Page<CommentEntity> commentEntityPage =  commentRepository.findByPostIdAndParentId(postId,
                null, pagable);

        PaginationData paginationData = PaginationData.builder()
                .total(commentEntityPage.getTotalElements())
                .pageNo(commentEntityPage.getNumber())
                .pageSize(commentEntityPage.getSize())
                .build();

        List<CommentEntity> commentEntities = commentEntityPage.getContent();

        return new ImmutablePair<>(commentEntities, paginationData);
    }
}
