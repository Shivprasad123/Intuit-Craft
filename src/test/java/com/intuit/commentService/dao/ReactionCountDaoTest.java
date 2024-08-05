package com.intuit.commentService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.entity.ReactionCountEntity;
import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.repository.ReactionCountRepository;

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

@ContextConfiguration(classes = {ReactionCountDao.class})
@ExtendWith(SpringExtension.class)
class ReactionCountDaoTest {
    @Autowired
    private ReactionCountDao reactionCountDao;

    @MockBean
    private ReactionCountRepository reactionCountRepository;

    /**
     * Method under test: {@link ReactionCountDao#get(String, String)}
     */
    @Test
    void testGet() {
        ArrayList<ReactionCountEntity> reactionCountEntityList = new ArrayList<>();
        Optional<List<ReactionCountEntity>> ofResult = Optional.of(reactionCountEntityList);
        when(reactionCountRepository.findByEntityTypeAndEntityId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(ofResult);
        List<ReactionCountEntity> actualGetResult = reactionCountDao.get("Entity Type", "42");
        verify(reactionCountRepository).findByEntityTypeAndEntityId(Mockito.<String>any(), Mockito.<String>any());
        assertTrue(actualGetResult.isEmpty());
        assertSame(reactionCountEntityList, actualGetResult);
    }

    /**
     * Method under test: {@link ReactionCountDao#get(String, String, Long)}
     */
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
        Optional<ReactionCountEntity> ofResult = Optional.of(reactionCountEntity);
        when(reactionCountRepository.findByEntityTypeAndEntityIdAndReactionMetaId(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<Long>any())).thenReturn(ofResult);
        Optional<ReactionCountEntity> actualGetResult = reactionCountDao.get("Entity Type", "42", 1L);
        verify(reactionCountRepository).findByEntityTypeAndEntityIdAndReactionMetaId(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<Long>any());
        assertTrue(actualGetResult.isPresent());
        assertSame(ofResult, actualGetResult);
    }

    /**
     * Method under test:
     * {@link ReactionCountDao#add(String, String, ReactionMetaEntity)}
     */
    @Test
    void testAdd() {
        ReactionMetaEntity reactionMeta = new ReactionMetaEntity();
        reactionMeta.setId(1L);
        reactionMeta.setType("Type");

        ReactionCountEntity reactionCountEntity = new ReactionCountEntity();
        reactionCountEntity.setCount(3L);
        reactionCountEntity.setEntityId("42");
        reactionCountEntity.setEntityType("Entity Type");
        reactionCountEntity.setReactionMeta(reactionMeta);
        when(reactionCountRepository.save(Mockito.<ReactionCountEntity>any())).thenReturn(reactionCountEntity);

        ReactionMetaEntity reactionMeta2 = new ReactionMetaEntity();
        reactionMeta2.setId(1L);
        reactionMeta2.setType("Type");
        ReactionCountEntity actualAddResult = reactionCountDao.add("Entity Type", "42", reactionMeta2);
        verify(reactionCountRepository).save(Mockito.<ReactionCountEntity>any());
        assertSame(reactionCountEntity, actualAddResult);
    }

    /**
     * Method under test:
     * {@link ReactionCountDao#incrementCount(String, String, Long)}
     */
    @Test
    void testIncrementCount() {
        when(reactionCountRepository.incrementReactionCount(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Long>any())).thenReturn(3);
        int actualIncrementCountResult = reactionCountDao.incrementCount("Entity Type", "42", 1L);
        verify(reactionCountRepository).incrementReactionCount(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Long>any());
        assertEquals(3, actualIncrementCountResult);
    }

    /**
     * Method under test:
     * {@link ReactionCountDao#decrementCount(String, String, Long)}
     */
    @Test
    void testDecrementCount() {
        when(reactionCountRepository.decrementReactionCount(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Long>any())).thenReturn(3);
        int actualDecrementCountResult = reactionCountDao.decrementCount("Entity Type", "42", 1L);
        verify(reactionCountRepository).decrementReactionCount(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Long>any());
        assertEquals(3, actualDecrementCountResult);
    }
}
