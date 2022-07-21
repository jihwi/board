package com.hbproject.board.services.comment.controller;

import com.hbproject.board.services.comment.dto.Comment;
import com.hbproject.board.services.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
/**
 * 댓글 CRUD 컨트롤러
 */
public class CommentController {

    private final CommentService commentService;

    /**
     * 포스트의 댓글리스트 조회
     *
     * @param model
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public String getCommentList(Model model, @PathVariable("postId") int postId, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

        List<Comment> commentList = commentService.getCommentList(postId, pageNum, pageSize);
        model.addAttribute("commentList", commentList);
        return "pages/comment/comment";
    }

    /**
     * 댓글 작성하기
     *
     * @param postId
     * @param comment
     * @return
     */
    @PostMapping("/{postId}")
    public String insertComment(@PathVariable("postId") int postId, Comment comment) {
        comment.setPostId(postId);
        if (comment.getReplyNo() == null) {
            comment.setReplyYn("N");
        } else {
            comment.setReplyYn("Y");
        }

        commentService.insertComment(comment);
        return "redirect:/posts/" + postId;
    }

    /**
     * 댓글 삭제하기
     *
     * @param no
     */
    @DeleteMapping("/{no}")
    @ResponseBody
    public void deleteComment(@RequestParam("no") int no) {
        commentService.deleteComment(no);
    }
}
