package com.hbproject.board.services.post.controller;

import com.hbproject.board.services.post.dto.Post;
import com.hbproject.board.services.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * main controller
 * post CURD
 */
@Controller
@RequiredArgsConstructor
@RequestMapping({"/", ""})
public class PostWebController {

    private final PostService postService;

    @GetMapping
    public String main() {
        return "redirect:/posts";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "/pages/post/register";
    }

    /**
     * post 리스트 조회
     *
     * @param model
     * @return
     */
    @GetMapping("/posts")
    public String getAllPostList(Model model) {
        List<Post> postList = postService.getAllPostList();
        model.addAttribute("postList", postList);
        return "pages/post/index";
    }

    /**
     * post 등록
     */
    @PostMapping("/posts")
    public String registerPost(Post post) {
        postService.registerPost(post);
        return "redirect:/posts";
    }

    /**
     * post 단건 조회
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable("postId") int postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "pages/post/post";
    }

    /**
     * post 단건 수정
     *
     * @param postId
     * @param post
     */
    @PostMapping("/posts/{postId}")
    public String modifyPost(@PathVariable("postId") int postId, Post post) {
        postService.modifyPost(postId, post);
        return "redirect:/posts";
    }

    /**
     * post 삭제
     */
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable("postId") int postId) {
        postService.deletePost(postId);
    }
}
