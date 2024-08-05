package com.intuit.commentService.service.implementation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.service.ICommentService;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostService.class})
@ExtendWith(SpringExtension.class)
class PostServiceTest {
    @MockBean
    private ICommentService iCommentService;

    @Autowired
    private PostService postService;

    @Test
    void testGetComments() {
        ImmutablePair<List<CommentResponse>, PaginationData> nullPairResult = ImmutablePair.nullPair();
        when(iCommentService.getByPostId(Mockito.<String>any(), Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(nullPairResult);
        Pair<List<CommentResponse>, PaginationData> actualComments = postService.getComments("42", 1, 3);
        verify(iCommentService).getByPostId(Mockito.<String>any(), Mockito.<Integer>any(), Mockito.<Integer>any());
        assertSame(nullPairResult, actualComments);
    }
}
