package com.hbproject.board.services.comment.mapper;

import com.hbproject.board.services.comment.dto.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {

    int insertComment(Comment comment);

    void deleteComment(int no);

    List<Comment> selectComments(@Param("postId") int postId);

}
