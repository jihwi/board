package com.hbproject.board.services.post.mapper;

import com.hbproject.board.common.paging.PageCriteria;
import com.hbproject.board.services.post.dto.Post;
import com.hbproject.board.services.post.dto.PostCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository {
    /**
     * Post 리스트 조회
     *
     * @return
     */
    List<Post> selectPostPageList(PostCriteria postCriteria);

    /**
     * Post 단건 조회
     *
     * @param postId
     * @return
     */
    Post selectPost(@Param("postId") int postId);

    /**
     * Post 등록
     *
     * @param post
     */
    void insertPost(Post post);

    /**
     * Post 수정
     *
     * @param postId
     * @param post
     */
    void updatePost(@Param("postId") int postId, @Param("post") Post post);

    /**
     * Post 삭제
     *
     * @param postId
     */
    void deletePost(int postId);

    /**
     * Post 수량 조회
     *
     * @return
     */
    int selectPostCount(PostCriteria postCriteria);
}
