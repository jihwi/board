package com.hbproject.board.services.user.controller;

import com.hbproject.board.services.user.dto.User;
import com.hbproject.board.services.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
/**
 *  회원가입 controller
 */
public class UserController {

    private final UserService userService;

    /**
     * 로그인 페이지
     *
     * @return
     */
    @GetMapping("/loginPage")
    public String loginPage() {
        return "/pages/login/login";
    }

    /**
     * 로그아웃 페이지
     *
     * @return
     */
    @GetMapping("/logout")
    public String logoutPage() {
        return "/pages/login/login";
    }

    /**
     * 회원가입 페이지
     *
     * @return
     */
    @GetMapping("/account")
    public String accoutPage() {
        return "/pages/login/register";
    }

    /**
     * 회원가입
     *
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/account")
    public String join(@Valid User user, Model model) {
        try {
            userService.createAccount(user);
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "이미 존재하는 아이디 입니다.");
            model.addAttribute("user", user);
            return "/pages/login/register";
        }

        return "pages/post/index";
    }
}
