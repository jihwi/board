package com.hbproject.board.services.comment.service;

import com.hbproject.board.services.comment.dto.Comment;
import com.hbproject.board.services.comment.mapper.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 댓글리스트 조회
     *
     * @param postId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Comment> getCommentList(int postId, int pageNum, int pageSize) {
        //댓글 전체 조회
        List<Comment> commentList = commentRepository.selectComments(postId);
        List<Comment> resultCommentList = new ArrayList<>();
        //대댓글이 아닌 root 댓글 리스트
        List<Comment> rootCommentList = commentList.stream().filter(comment -> "N".equals(comment.getReplyYn())).sorted(Comparator.comparing(Comment::getNo)).collect(Collectors.toList());

        for (Comment comment : rootCommentList) {
            resultCommentList.add(comment);
            commentList.remove(comment);
            //대댓글 리스트를 가져온다.
            this.getReplyComments(comment.getNo(), commentList, resultCommentList);
        }

        return resultCommentList;
    }

    /**
     * 대댓글 리스트 조회 (재귀호출)
     *
     * @param no
     * @param commentList
     * @param resultCommentList
     */
    private void getReplyComments(int no, List<Comment> commentList, List<Comment> resultCommentList) {
        //대댓글을 뽑아, 정렬한다.
        List<Comment> replyCommentList = commentList.stream()
                .filter(comment -> comment.getReplyNo() != null && comment.getReplyNo() == no)
                .sorted(Comparator.comparing(Comment::getNo))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(replyCommentList)) {
            //for문을 돌면서 대댓글의 대대댓글을 담아온다.
            for (Comment comment : replyCommentList) {
                resultCommentList.add(comment);
                commentList.remove(comment);
                //대댓글 리스트 조회
                this.getReplyComments(comment.getNo(), commentList, resultCommentList);
            }
        }
        return;
    }

    /**
     * 댓글 입력
     *
     * @param comment
     * @return
     */
    public int insertComment(Comment comment) {
        return commentRepository.insertComment(comment);
    }

    /**
     * 댓글 삭제
     *
     * @param no
     */
    public void deleteComment(int no) {
        commentRepository.deleteComment(no);
    }

}
