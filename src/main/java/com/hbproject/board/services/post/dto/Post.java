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
    private int PostId;
    /**
     * 타이틀
     */
    private String Tit;
    /**
     * 내용
     */
    private String Conts;
    /**
     * 입력자
     */
    private String InsId;
    /**
     * 입력일시
     */
    private LocalDateTime InsDtm;
    /**
     * 수정자
     */
    private String ModId;
    /**
     * 수정일시
     */
    private LocalDateTime ModDtm;
}
