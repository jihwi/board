package com.hbproject.board.services.post.controller;

import com.hbproject.board.services.post.dto.Post;
import com.hbproject.board.services.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping({"/", ""})
public class PostWebController {

    private final PostService postService;

    @GetMapping
    public String main() {
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String getAllPostList(Model model) {
        List<Post> postList = postService.getAllPostList();
        model.addAttribute("postList", postList);
        return "pages/post/index";
    }
}
