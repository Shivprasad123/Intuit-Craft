package com.intuit.commentService.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.dao.ReactionDao;
import com.intuit.commentService.dao.ReactionMetaDao;
import com.intuit.commentService.dao.UserDao;
import com.intuit.commentService.design_pattern.strategy.addReaction.IAddReactionStrategy;
import com.intuit.commentService.dto.Request.ReactionRequest;
import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.dto.Response.ReactionResponse;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.entity.UsersEntity;
import com.intuit.commentService.service.IReactionCountService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReactionService.class})
@ExtendWith(SpringExtension.class)
class ReactionServiceTest {
    @MockBean
    private IAddReactionStrategy iAddReactionStrategy;

    @MockBean
    private IReactionCountService iReactionCountService;

    @MockBean
    private ReactionDao reactionDao;

    @MockBean
    private ReactionMetaDao reactionMetaDao;

    @Autowired
    private ReactionService reactionService;

    @MockBean
    private UserDao userDao;



    @Test
    void testAdd() {
        doNothing().when(iAddReactionStrategy).add(Mockito.<ReactionRequest>any());

        ReactionRequest reactionRequest = new ReactionRequest();
        reactionRequest.setEntityId("42");
        reactionRequest.setEntityType("Entity Type");
        reactionRequest.setReactionMetaId(1L);
        reactionRequest.setUserId("42");
        reactionService.add(reactionRequest);
        verify(iAddReactionStrategy).add(Mockito.<ReactionRequest>any());
        assertEquals("42", reactionRequest.getEntityId());
        assertEquals("42", reactionRequest.getUserId());
        assertEquals("Entity Type", reactionRequest.getEntityType());
        assertEquals(1L, reactionRequest.getReactionMetaId().longValue());
    }


    @Test
    void testDelete() {
        when(iReactionCountService.decrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(3);

        ReactionMetaEntity reactionMeta = new ReactionMetaEntity();
        reactionMeta.setId(1L);
        reactionMeta.setType("Type");

        UsersEntity users = new UsersEntity();
        users.setActive(true);
        users.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setEmail("jane.doe@example.org");
        users.setId("42");
        users.setProfileUrl("https://example.org/example");
        users.setUserName("janedoe");

        ReactionEntity reactionEntity = new ReactionEntity();
        reactionEntity.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reactionEntity.setEntityId("42");
        reactionEntity.setEntityType("Entity Type");
        reactionEntity.setReactionMeta(reactionMeta);
        reactionEntity.setUsers(users);
        Optional<ReactionEntity> ofResult = Optional.of(reactionEntity);
        when(reactionDao.delete(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any())).thenReturn(1);
        when(reactionDao.get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);
        reactionService.delete("Entity Type", "42", "42");
        verify(reactionDao).delete(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());
        verify(reactionDao).get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());
        verify(iReactionCountService).decrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
    }


    @Test
    void testDelete2() {
        ReactionMetaEntity reactionMeta = new ReactionMetaEntity();
        reactionMeta.setId(1L);
        reactionMeta.setType("Type");

        UsersEntity users = new UsersEntity();
        users.setActive(true);
        users.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setEmail("jane.doe@example.org");
        users.setId("42");
        users.setProfileUrl("https://example.org/example");
        users.setUserName("janedoe");

        ReactionEntity reactionEntity = new ReactionEntity();
        reactionEntity.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reactionEntity.setEntityId("42");
        reactionEntity.setEntityType("Entity Type");
        reactionEntity.setReactionMeta(reactionMeta);
        reactionEntity.setUsers(users);
        Optional<ReactionEntity> ofResult = Optional.of(reactionEntity);
        when(reactionDao.delete(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any())).thenReturn(0);
        when(reactionDao.get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);
        reactionService.delete("Entity Type", "42", "42");
        verify(reactionDao).delete(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());
        verify(reactionDao).get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());
    }


    @Test
    void testDelete3() {
        Optional<ReactionEntity> emptyResult = Optional.empty();
        when(reactionDao.get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);
        reactionService.delete("Entity Type", "42", "42");
        verify(reactionDao).get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());
    }
}
