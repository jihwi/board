package com.hbproject.board.services.post.service;

import com.hbproject.board.common.paging.PageCriteria;
import com.hbproject.board.services.post.dto.Post;
import com.hbproject.board.services.post.dto.PostCriteria;
import com.hbproject.board.services.post.mapper.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Post CRUD
 */
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    /**
     * Post 리스트 조회
     *
     * @return
     */
    public List<Post> getPostPageList(PostCriteria postCriteria) {
        return postRepository.selectPostPageList(postCriteria);
    }

    /**
     * Post 단건 조회
     *
     * @param postId
     * @return
     */
    public Post getPost(int postId) {
        return postRepository.selectPost(postId);
    }

    /**
     * Post 등록
     *
     * @param post
     */
    public void registerPost(Post post) {
        postRepository.insertPost(post);
    }

    /**
     * Post 수정
     *
     * @param postId
     * @param post
     */
    public void modifyPost(int postId, Post post) {
        postRepository.updatePost(postId, post);
    }

    /**
     * Post 삭제
     *
     * @param postId
     */
    public void deletePost(int postId) {
        postRepository.deletePost(postId);
    }
}
