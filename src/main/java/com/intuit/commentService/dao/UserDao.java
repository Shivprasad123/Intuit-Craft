package com.intuit.commentService.dao;

import com.intuit.commentService.entity.UsersEntity;
import com.intuit.commentService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

    public UsersEntity getUserById(String userId){
        return userRepository.getById(userId);
    }
}
