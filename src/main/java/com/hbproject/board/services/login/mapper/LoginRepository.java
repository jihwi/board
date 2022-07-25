package com.hbproject.board.services.login.mapper;

import com.hbproject.board.services.login.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository {
    int insertUser(User user);

    User selectUser(User user);
}
