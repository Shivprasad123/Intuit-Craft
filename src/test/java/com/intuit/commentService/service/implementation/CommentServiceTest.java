package com.intuit.commentService.service.implementation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.dao.CommentDao;
import com.intuit.commentService.design_pattern.strategy.addComment.IAddCommentStrategy;
import com.intuit.commentService.dto.Request.Comment;
import com.intuit.commentService.dto.Response.CommentResponse;
import com.intuit.commentService.dto.Response.Users;
import com.intuit.commentService.exception.CustomException;
import com.intuit.commentService.service.IReactionCountService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommentService.class})
@ExtendWith(SpringExtension.class)
class CommentServiceTest {
    @MockBean
    private CommentDao commentDao;

    @Autowired
    private CommentService commentService;

    @MockBean
    private IAddCommentStrategy iAddCommentStrategy;

    @MockBean
    private IReactionCountService iReactionCountService;

    @Test
    void testAdd() {
        Users user = new Users();
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setId("42");
        user.setProfileUrl("https://example.org/example");
        user.setUserName("janedoe");

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent("Not all who wander are lost");
        commentResponse.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        commentResponse.setEdited(true);
        commentResponse.setId("42");
        commentResponse.setParentId("42");
        commentResponse.setPostId("42");
        commentResponse.setReactionCount(new ArrayList<>());
        commentResponse.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        commentResponse.setUser(user);
        commentResponse.setUserId("42");
        when(iAddCommentStrategy.add(Mockito.<Comment>any())).thenReturn(commentResponse);

        Comment commentRequest = new Comment();
        commentRequest.setContent("Not all who wander are lost");
        commentRequest.setParentId("42");
        commentRequest.setPostId("42");
        commentRequest.setUserId("42");
        CommentResponse actualAddResult = commentService.add(commentRequest);
        verify(iAddCommentStrategy).add(Mockito.<Comment>any());
        assertSame(commentResponse, actualAddResult);
    }

    @Test
    void testAdd2() {
        when(iAddCommentStrategy.add(Mockito.<Comment>any()))
                .thenThrow(new CustomException("An error occurred", "An error occurred"));

        Comment commentRequest = new Comment();
        commentRequest.setContent("Not all who wander are lost");
        commentRequest.setParentId("42");
        commentRequest.setPostId("42");
        commentRequest.setUserId("42");
        assertThrows(CustomException.class, () -> commentService.add(commentRequest));
        verify(iAddCommentStrategy).add(Mockito.<Comment>any());
    }
}
