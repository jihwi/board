package com.hbproject.board.services.user.mapper;

import com.hbproject.board.services.user.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    /**
     * user 등록
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * id로 user 정보 조회
     *
     * @param id
     * @return
     */
    User findById(String id);
}
