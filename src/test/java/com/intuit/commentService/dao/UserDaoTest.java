package com.intuit.commentService.dao;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.intuit.commentService.entity.UsersEntity;
import com.intuit.commentService.repository.UserRepository;

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

@ContextConfiguration(classes = {UserDao.class})
@ExtendWith(SpringExtension.class)
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserDao#getUserById(String)}
     */
    @Test
    void testGetUserById() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setActive(true);
        usersEntity.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        usersEntity.setEmail("jane.doe@example.org");
        usersEntity.setId("42");
        usersEntity.setProfileUrl("https://example.org/example");
        usersEntity.setUserName("janedoe");
        when(userRepository.getById(Mockito.<String>any())).thenReturn(usersEntity);
        UsersEntity actualUserById = userDao.getUserById("42");
        verify(userRepository).getById(Mockito.<String>any());
        assertSame(usersEntity, actualUserById);
    }
}
