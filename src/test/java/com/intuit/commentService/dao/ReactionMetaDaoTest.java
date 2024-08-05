package com.intuit.commentService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.entity.ReactionMetaEntity;
import com.intuit.commentService.repository.ReactionMetaRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReactionMetaDao.class})
@ExtendWith(SpringExtension.class)
class ReactionMetaDaoTest {
    @Autowired
    private ReactionMetaDao reactionMetaDao;

    @MockBean
    private ReactionMetaRepository reactionMetaRepository;

    /**
     * Method under test: {@link ReactionMetaDao#getByReactionMetaId(Long)}
     */
    @Test
    void testGetByReactionMetaId() {
        ReactionMetaEntity reactionMetaEntity = new ReactionMetaEntity();
        reactionMetaEntity.setId(1L);
        reactionMetaEntity.setType("Type");
        when(reactionMetaRepository.getById(Mockito.<Long>any())).thenReturn(reactionMetaEntity);
        ReactionMetaEntity actualByReactionMetaId = reactionMetaDao.getByReactionMetaId(1L);
        verify(reactionMetaRepository).getById(Mockito.<Long>any());
        assertSame(reactionMetaEntity, actualByReactionMetaId);
    }

    /**
     * Method under test: {@link ReactionMetaDao#getReactionTypeByMetaId(Long)}
     */
    @Test
    void testGetReactionTypeByMetaId() {
        ReactionMetaEntity reactionMetaEntity = new ReactionMetaEntity();
        reactionMetaEntity.setId(1L);
        reactionMetaEntity.setType("Type");
        when(reactionMetaRepository.getById(Mockito.<Long>any())).thenReturn(reactionMetaEntity);
        String actualReactionTypeByMetaId = reactionMetaDao.getReactionTypeByMetaId(1L);
        verify(reactionMetaRepository).getById(Mockito.<Long>any());
        assertEquals("Type", actualReactionTypeByMetaId);
    }

    /**
     * Method under test: {@link ReactionMetaDao#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<ReactionMetaEntity> reactionMetaEntityList = new ArrayList<>();
        when(reactionMetaRepository.findAll()).thenReturn(reactionMetaEntityList);
        List<ReactionMetaEntity> actualAll = reactionMetaDao.getAll();
        verify(reactionMetaRepository).findAll();
        assertTrue(actualAll.isEmpty());
        assertSame(reactionMetaEntityList, actualAll);
    }
}
