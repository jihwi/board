package com.hbproject.board.services.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    /**
     * 게시물 번호
     */
    private int postId;
    /**
     * 타이틀
     */
    private String tit;
    /**
     * 내용
     */
    private String conts;
    /**
     * 입력자
     */
    private String insId;
    /**
     * 입력일시
     */
    private LocalDateTime insDtm;
    /**
     * 수정자
     */
    private String modId;
    /**
     * 수정일시
     */
    private LocalDateTime modDtm;
}
