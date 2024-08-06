package com.intuit.commentService.dao;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.entity.CommentEntity;
import com.intuit.commentService.entity.PostEntity;
import com.intuit.commentService.entity.UsersEntity;
import com.intuit.commentService.repository.CommentRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommentDao.class})
@ExtendWith(SpringExtension.class)
class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    void testAdd() {
        CommentEntity parent = new CommentEntity();
        parent.setContent("Not all who wander are lost");
        parent.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent.setId("42");
        parent.setIsEdited(true);
        parent.setParent(new CommentEntity());
        parent.setPost(new PostEntity());
        parent.setType("Type");
        parent.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent.setUser(new UsersEntity());

        PostEntity post = new PostEntity();
        post.setContent("Not all who wander are lost");
        post.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post.setId("42");
        post.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post.setUsers(new UsersEntity());

        UsersEntity user = new UsersEntity();
        user.setActive(true);
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setProfileUrl("https://example.org/example");
        user.setUserName("janedoe");

        CommentEntity parent2 = new CommentEntity();
        parent2.setContent("Not all who wander are lost");
        parent2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent2.setId("42");
        parent2.setIsEdited(true);
        parent2.setParent(parent);
        parent2.setPost(post);
        parent2.setType("Type");
        parent2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent2.setUser(user);

        UsersEntity users = new UsersEntity();
        users.setActive(true);
        users.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setEmail("jane.doe@example.org");
        users.setId("42");
        users.setProfileUrl("https://example.org/example");
        users.setUserName("janedoe");

        PostEntity post2 = new PostEntity();
        post2.setContent("Not all who wander are lost");
        post2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post2.setId("42");
        post2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post2.setUsers(users);

        UsersEntity user2 = new UsersEntity();
        user2.setActive(true);
        user2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setEmail("jane.doe@example.org");
        user2.setId("42");
        user2.setProfileUrl("https://example.org/example");
        user2.setUserName("janedoe");

        CommentEntity parent3 = new CommentEntity();
        parent3.setContent("Not all who wander are lost");
        parent3.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent3.setId("42");
        parent3.setIsEdited(true);
        parent3.setParent(parent2);
        parent3.setPost(post2);
        parent3.setType("Type");
        parent3.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent3.setUser(user2);

        UsersEntity users2 = new UsersEntity();
        users2.setActive(true);
        users2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setEmail("jane.doe@example.org");
        users2.setId("42");
        users2.setProfileUrl("https://example.org/example");
        users2.setUserName("janedoe");

        PostEntity post3 = new PostEntity();
        post3.setContent("Not all who wander are lost");
        post3.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post3.setId("42");
        post3.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post3.setUsers(users2);

        UsersEntity user3 = new UsersEntity();
        user3.setActive(true);
        user3.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user3.setEmail("jane.doe@example.org");
        user3.setId("42");
        user3.setProfileUrl("https://example.org/example");
        user3.setUserName("janedoe");

        CommentEntity parent4 = new CommentEntity();
        parent4.setContent("Not all who wander are lost");
        parent4.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent4.setId("42");
        parent4.setIsEdited(true);
        parent4.setParent(parent3);
        parent4.setPost(post3);
        parent4.setType("Type");
        parent4.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent4.setUser(user3);

        UsersEntity users3 = new UsersEntity();
        users3.setActive(true);
        users3.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users3.setEmail("jane.doe@example.org");
        users3.setId("42");
        users3.setProfileUrl("https://example.org/example");
        users3.setUserName("janedoe");

        PostEntity post4 = new PostEntity();
        post4.setContent("Not all who wander are lost");
        post4.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post4.setId("42");
        post4.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post4.setUsers(users3);

        UsersEntity user4 = new UsersEntity();
        user4.setActive(true);
        user4.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user4.setEmail("jane.doe@example.org");
        user4.setId("42");
        user4.setProfileUrl("https://example.org/example");
        user4.setUserName("janedoe");

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent("Not all who wander are lost");
        commentEntity.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        commentEntity.setId("42");
        commentEntity.setIsEdited(true);
        commentEntity.setParent(parent4);
        commentEntity.setPost(post4);
        commentEntity.setType("Type");
        commentEntity.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        commentEntity.setUser(user4);
        when(commentRepository.save(Mockito.<CommentEntity>any())).thenReturn(commentEntity);

        CommentEntity parent5 = new CommentEntity();
        parent5.setContent("Not all who wander are lost");
        parent5.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent5.setId("42");
        parent5.setIsEdited(true);
        parent5.setParent(new CommentEntity());
        parent5.setPost(new PostEntity());
        parent5.setType("Type");
        parent5.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent5.setUser(new UsersEntity());

        PostEntity post5 = new PostEntity();
        post5.setContent("Not all who wander are lost");
        post5.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post5.setId("42");
        post5.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post5.setUsers(new UsersEntity());

        UsersEntity user5 = new UsersEntity();
        user5.setActive(true);
        user5.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user5.setEmail("jane.doe@example.org");
        user5.setId("42");
        user5.setProfileUrl("https://example.org/example");
        user5.setUserName("janedoe");

        CommentEntity parent6 = new CommentEntity();
        parent6.setContent("Not all who wander are lost");
        parent6.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent6.setId("42");
        parent6.setIsEdited(true);
        parent6.setParent(parent5);
        parent6.setPost(post5);
        parent6.setType("Type");
        parent6.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent6.setUser(user5);

        UsersEntity users4 = new UsersEntity();
        users4.setActive(true);
        users4.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users4.setEmail("jane.doe@example.org");
        users4.setId("42");
        users4.setProfileUrl("https://example.org/example");
        users4.setUserName("janedoe");

        PostEntity post6 = new PostEntity();
        post6.setContent("Not all who wander are lost");
        post6.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post6.setId("42");
        post6.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post6.setUsers(users4);

        UsersEntity user6 = new UsersEntity();
        user6.setActive(true);
        user6.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user6.setEmail("jane.doe@example.org");
        user6.setId("42");
        user6.setProfileUrl("https://example.org/example");
        user6.setUserName("janedoe");

        CommentEntity parent7 = new CommentEntity();
        parent7.setContent("Not all who wander are lost");
        parent7.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent7.setId("42");
        parent7.setIsEdited(true);
        parent7.setParent(parent6);
        parent7.setPost(post6);
        parent7.setType("Type");
        parent7.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        parent7.setUser(user6);

        UsersEntity users5 = new UsersEntity();
        users5.setActive(true);
        users5.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users5.setEmail("jane.doe@example.org");
        users5.setId("42");
        users5.setProfileUrl("https://example.org/example");
        users5.setUserName("janedoe");

        PostEntity post7 = new PostEntity();
        post7.setContent("Not all who wander are lost");
        post7.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post7.setId("42");
        post7.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        post7.setUsers(users5);

        UsersEntity user7 = new UsersEntity();
        user7.setActive(true);
        user7.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user7.setEmail("jane.doe@example.org");
        user7.setId("42");
        user7.setProfileUrl("https://example.org/example");
        user7.setUserName("janedoe");

        CommentEntity commentEntity2 = new CommentEntity();
        commentEntity2.setContent("Not all who wander are lost");
        commentEntity2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        commentEntity2.setId("42");
        commentEntity2.setIsEdited(true);
        commentEntity2.setParent(parent7);
        commentEntity2.setPost(post7);
        commentEntity2.setType("Type");
        commentEntity2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        commentEntity2.setUser(user7);
        CommentEntity actualAddResult = commentDao.add(commentEntity2);
        assertSame(commentEntity, actualAddResult);
    }
}
