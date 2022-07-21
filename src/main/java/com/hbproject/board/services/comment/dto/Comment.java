package com.hbproject.board.services.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
/**
 * 댓글
 */
public class Comment {

    /**
     * 댓글 번호
     */
    private int no;
    /**
     * 포스트 번호
     */
    private int postId;
    /**
     * 댓글 내용
     */
    private String conts;
    /**
     * 답글여부 (Y/N)
     */
    private String replyYn;
    /**
     * 답글 댓글 번호
     */
    private Integer replyNo;
    /**
     * 댓글 노출 순번
     */
    private Integer commentOrder;
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
