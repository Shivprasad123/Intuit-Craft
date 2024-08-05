package com.intuit.commentService.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.dao.ReactionCountDao;
import com.intuit.commentService.dao.ReactionMetaDao;
import com.intuit.commentService.dto.Response.ReactionCountResponse;
import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReactionCountService.class})
@ExtendWith(SpringExtension.class)
class ReactionCountServiceTest {
    @MockBean
    private ReactionCountDao reactionCountDao;

    @Autowired
    private ReactionCountService reactionCountService;

    @MockBean
    private ReactionMetaDao reactionMetaDao;

    @Test
    void testGet() {
        when(reactionCountDao.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(reactionMetaDao.getAll()).thenReturn(new ArrayList<>());
        List<ReactionCountResponse> actualGetResult = reactionCountService.get("Entity Type", "42");
        verify(reactionCountDao).get(Mockito.<String>any(), Mockito.<String>any());
        verify(reactionMetaDao).getAll();
        assertTrue(actualGetResult.isEmpty());
    }

    @Test
    void testGet2() {
        ReactionMetaEntity reactionMeta = new ReactionMetaEntity();
        reactionMeta.setId(1L);
        reactionMeta.setType("Type");

        ReactionCountEntity reactionCountEntity = new ReactionCountEntity();
        reactionCountEntity.setCount(3L);
        reactionCountEntity.setEntityId("42");
        reactionCountEntity.setEntityType("Entity Type");
        reactionCountEntity.setReactionMeta(reactionMeta);

        ArrayList<ReactionCountEntity> reactionCountEntityList = new ArrayList<>();
        reactionCountEntityList.add(reactionCountEntity);
        when(reactionCountDao.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn(reactionCountEntityList);
        when(reactionMetaDao.getAll()).thenReturn(new ArrayList<>());
        List<ReactionCountResponse> actualGetResult = reactionCountService.get("Entity Type", "42");
        verify(reactionCountDao).get(Mockito.<String>any(), Mockito.<String>any());
        verify(reactionMetaDao).getAll();
        assertEquals(1, actualGetResult.size());
        ReactionCountResponse getResult = actualGetResult.get(0);
        assertEquals("Type", getResult.getType());
        assertEquals(1L, getResult.getReactionMetaId().longValue());
        assertEquals(3L, getResult.getCount().longValue());
    }


    @Test
    void testGet3() {
        when(reactionCountDao.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());

        ReactionMetaEntity reactionMetaEntity = new ReactionMetaEntity();
        reactionMetaEntity.setId(1L);
        reactionMetaEntity.setType("Type");

        ArrayList<ReactionMetaEntity> reactionMetaEntityList = new ArrayList<>();
        reactionMetaEntityList.add(reactionMetaEntity);
        when(reactionMetaDao.getAll()).thenReturn(reactionMetaEntityList);
        List<ReactionCountResponse> actualGetResult = reactionCountService.get("Entity Type", "42");
        verify(reactionCountDao).get(Mockito.<String>any(), Mockito.<String>any());
        verify(reactionMetaDao).getAll();
        assertEquals(1, actualGetResult.size());
        ReactionCountResponse getResult = actualGetResult.get(0);
        assertEquals("Type", getResult.getType());
        assertEquals(0L, getResult.getCount().longValue());
        assertEquals(1L, getResult.getReactionMetaId().longValue());
    }

    @Test
    void testGet4() {
        ReactionMetaEntity reactionMeta = new ReactionMetaEntity();
        reactionMeta.setId(3L);
        reactionMeta.setType("42");

        ReactionCountEntity reactionCountEntity = new ReactionCountEntity();
        reactionCountEntity.setCount(0L);
        reactionCountEntity.setEntityId("com.intuit.commentService.entity.ReactionCountEntity");
        reactionCountEntity.setEntityType("42");
        reactionCountEntity.setReactionMeta(reactionMeta);

        ReactionMetaEntity reactionMeta2 = new ReactionMetaEntity();
        reactionMeta2.setId(1L);
        reactionMeta2.setType("Type");

        ReactionCountEntity reactionCountEntity2 = new ReactionCountEntity();
        reactionCountEntity2.setCount(3L);
        reactionCountEntity2.setEntityId("42");
        reactionCountEntity2.setEntityType("Entity Type");
        reactionCountEntity2.setReactionMeta(reactionMeta2);

        ArrayList<ReactionCountEntity> reactionCountEntityList = new ArrayList<>();
        reactionCountEntityList.add(reactionCountEntity2);
        reactionCountEntityList.add(reactionCountEntity);
        when(reactionCountDao.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn(reactionCountEntityList);

        ReactionMetaEntity reactionMetaEntity = new ReactionMetaEntity();
        reactionMetaEntity.setId(1L);
        reactionMetaEntity.setType("Type");

        ArrayList<ReactionMetaEntity> reactionMetaEntityList = new ArrayList<>();
        reactionMetaEntityList.add(reactionMetaEntity);
        when(reactionMetaDao.getAll()).thenReturn(reactionMetaEntityList);
        List<ReactionCountResponse> actualGetResult = reactionCountService.get("Entity Type", "42");
        verify(reactionCountDao).get(Mockito.<String>any(), Mockito.<String>any());
        verify(reactionMetaDao).getAll();
        assertEquals(2, actualGetResult.size());
        ReactionCountResponse getResult = actualGetResult.get(1);
        assertEquals("42", getResult.getType());
        ReactionCountResponse getResult2 = actualGetResult.get(0);
        assertEquals("Type", getResult2.getType());
        assertEquals(0L, getResult.getCount().longValue());
        assertEquals(1L, getResult2.getReactionMetaId().longValue());
        assertEquals(3L, getResult2.getCount().longValue());
        assertEquals(3L, getResult.getReactionMetaId().longValue());
    }

    @Test
    void testDecrementCount() {
        when(reactionCountDao.decrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(3);
        int actualDecrementCountResult = reactionCountService.decrementCount("Entity Type", "42", 1L);
        verify(reactionCountDao).decrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
        assertEquals(3, actualDecrementCountResult);
    }


    @Test
    void testIncrementCount() {
        when(reactionCountDao.incrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(3);
        int actualIncrementCountResult = reactionCountService.incrementCount("Entity Type", "42", 1L);
        verify(reactionCountDao).incrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
        assertEquals(3, actualIncrementCountResult);
    }


    @Test
    void testIncrementCount2() {
        ReactionMetaEntity reactionMeta = new ReactionMetaEntity();
        reactionMeta.setId(1L);
        reactionMeta.setType("Type");

        ReactionCountEntity reactionCountEntity = new ReactionCountEntity();
        reactionCountEntity.setCount(3L);
        reactionCountEntity.setEntityId("42");
        reactionCountEntity.setEntityType("Entity Type");
        reactionCountEntity.setReactionMeta(reactionMeta);
        Optional<ReactionCountEntity> ofResult = Optional.of(reactionCountEntity);
        when(reactionCountDao.incrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(0);
        when(reactionCountDao.get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any())).thenReturn(ofResult);
        int actualIncrementCountResult = reactionCountService.incrementCount("Entity Type", "42", 1L);
        verify(reactionCountDao).get(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
        verify(reactionCountDao).incrementCount(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Long>any());
        assertEquals(0, actualIncrementCountResult);
    }
}
