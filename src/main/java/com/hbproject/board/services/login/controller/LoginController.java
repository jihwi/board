package com.hbproject.board.services.login.controller;

import com.hbproject.board.services.login.dto.User;
import com.hbproject.board.services.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
/**
 * 로그인, 회원가입 controller
 */
public class LoginController {

    private final LoginService loginService;

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "/pages/login/login";
    }

    /**
     * 로그인
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/check")
    public String loginCheck(User user, Model model){
        boolean valid = loginService.checkUser(user);

        if(valid){
            return "pages/post/index";
        }
        model.addAttribute("error", "아이디, 패스워드를 확인해주세요.");
        model.addAttribute("user", user);
        return "/pages/login/login";
    }

    /**
     * 회원가입 페이지
     * @return
     */
    @GetMapping("/account")
    public String accoutPage(){
        return "/pages/login/register";
    }

    /**
     * 회원가입
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/account")
    public String register(@Valid User user, Model model){
        try {
            loginService.createAccount(user);
        }catch (DuplicateKeyException e){
            model.addAttribute("error", "이미 존재하는 아이디 입니다.");
            model.addAttribute("user", user);
            return "/pages/login/register";
        }

        return "pages/post/index";
    }
}
