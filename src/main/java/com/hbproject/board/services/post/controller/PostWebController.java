package com.hbproject.board.services.post.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbproject.board.services.post.dto.Post;
import com.hbproject.board.services.post.dto.PostCriteria;
import com.hbproject.board.services.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * main controller
 * post CRUD
 */
@Controller
@RequiredArgsConstructor
@RequestMapping({"/", ""})
@Slf4j
public class PostWebController {

    private final PostService postService;

    @GetMapping
    public String main() {
        return "pages/post/index";
    }

    /**
     * 포스트 등록 페이지 이동
     *
     * @return
     */
    @GetMapping("/register")
    public String registerPage() {
        return "/pages/post/register";
    }

    /**
     * 포스트 수정 페이지 이동
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping("/modify")
    public String modifyPage(@RequestParam("postId") int postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "/pages/post/register";
    }


    /**
     * post 리스트 조회
     *
     * @param model
     * @return
     */
    @GetMapping("/posts")
    public String getPostPageList(PostCriteria postCriteria, Model model) {
        log.debug("postCriteria:{}", postCriteria.toString());

        //mybatis PageHelper 사용
        PageHelper.startPage(postCriteria);
        List<Post> postList = postService.getPostPageList(postCriteria);
        PageInfo<Post> postPageInfo = PageInfo.of(postList, 5);
        model.addAttribute("postPageInfo", postPageInfo);
        return "pages/post/index :: #postList";
    }

    /**
     * post 등록
     */
    //PRG 패턴(Post - Redirect - Get)
    @PostMapping("/posts")
    public String registerPost(Post post) {
        postService.registerPost(post);
        return "redirect:/";
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
        return "redirect:/";
    }

    /**
     * post 삭제
     */
    //Controller에서 void타입은 현재 페이지 view를 리턴하게 되므로 void 시 에러발생 (삭제되엇기 때문)
    //delete일때는 post일때랑 다르게 redirect:/posts시 동일 delete method로 날라감.
    //ajax는 부분 수정이기 때문에 redirect와 맞지 않음. 페이지 이동을 하고 싶다면 form형태로 하면됨.
    @DeleteMapping("/posts/{postId}")
    @ResponseBody
    public String deletePost(@PathVariable("postId") int postId) {
        postService.deletePost(postId);
        return "포스트가 삭제되었습니다.";
    }
//    @DeleteMapping("/posts/{postId}")
//    public String deletePost(@PathVariable("postId") int postId) {
//        postService.deletePost(postId);
//        return "redirect:/posts";
//    }

}
