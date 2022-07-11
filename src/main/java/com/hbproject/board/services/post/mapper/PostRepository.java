package com.hbproject.board.services.post.mapper;

import com.hbproject.board.services.post.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository {
    List<Post> selectAllList();
}
