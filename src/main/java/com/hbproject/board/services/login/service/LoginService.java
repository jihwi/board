package com.hbproject.board.services.login.service;

import com.github.pagehelper.PageInfo;
import com.hbproject.board.services.login.dto.User;
import com.hbproject.board.services.login.mapper.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    public int createAccount(User user) throws DuplicateKeyException{
            return loginRepository.insertUser(user);
    }

    public boolean checkUser(User user) {
        User selectUser = loginRepository.selectUser(user);
        if(Objects.nonNull(selectUser)){
            return true;
        }
        return false;
    }
}
