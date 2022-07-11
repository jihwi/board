package com.hbproject.board.services.post.service;

import com.hbproject.board.services.post.dto.Post;
import com.hbproject.board.services.post.mapper.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPostList() {
        return postRepository.selectAllList();
    }
}
