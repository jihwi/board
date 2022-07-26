package com.hbproject.board.services.user.service;

import com.hbproject.board.services.user.dto.User;
import com.hbproject.board.services.user.mapper.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * user 등록
     *
     * @param user
     * @return
     * @throws DuplicateKeyException
     */
    public int createAccount(User user) throws DuplicateKeyException {
        //비밀번호 암호화
        user.setPw(bCryptPasswordEncoder.encode(user.getPw()));
        return userRepository.insertUser(user);
    }
}
