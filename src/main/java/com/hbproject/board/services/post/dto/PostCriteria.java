package com.hbproject.board.services.post.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PostCriteria {
    /**
     * 키워드 (타이틀 + 내용)
     */
    private String keyword;
    /**
     * 글쓴이
     */
    private String author;
    /**
     * 일자
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    /**
     * 페이징
     */
    private int pageNum = 1;    //현재페이지 번호
    private int pageSize = 5;   //페이지당 사이즈
}
