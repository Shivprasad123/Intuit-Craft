package com.intuit.commentService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.dto.Response.PaginationData;
import com.intuit.commentService.entity.ReactionEntity;
import com.intuit.commentService.repository.ReactionRepository;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReactionDao.class})
@ExtendWith(SpringExtension.class)
class ReactionDaoTest {
    @Autowired
    private ReactionDao reactionDao;

    @MockBean
    private ReactionRepository reactionRepository;


    @Test
    void testGet2() {
        Optional<ReactionEntity> ofResult = Optional.of(mock(ReactionEntity.class));
        when(reactionRepository.findByEntityTypeAndEntityIdAndUsersId(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(ofResult);
        Optional<ReactionEntity> actualGetResult = reactionDao.get("Entity Type", "42", "42");
        verify(reactionRepository).findByEntityTypeAndEntityIdAndUsersId(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any());
        assertTrue(actualGetResult.isPresent());
        assertSame(ofResult, actualGetResult);
    }

    @Test
    void testDelete() {
        when(reactionRepository.deleteByEntityTypeAndEntityIdAndUsersId(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(1);
        int actualDeleteResult = reactionDao.delete("Entity Type", "42", "42");
        verify(reactionRepository).deleteByEntityTypeAndEntityIdAndUsersId(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any());
        assertEquals(1, actualDeleteResult);
    }

    @Test
    void testAdd() {
        when(reactionRepository.save(Mockito.<ReactionEntity>any())).thenReturn(mock(ReactionEntity.class));
        reactionDao.add(mock(ReactionEntity.class));
        verify(reactionRepository).save(Mockito.<ReactionEntity>any());
    }
}
