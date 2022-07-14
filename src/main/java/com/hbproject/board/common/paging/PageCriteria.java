package com.hbproject.board.common.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Deprecated
/**
 * mybatis용 PageHelper 라이브러리 사용
 */
public class PageCriteria {
    /**
     * 현재페이지
     */
    private int nowPage = 1;
    /**
     * 시작페이지
     */
    private int startPage = 1;
    /**
     * 끝 페이지
     */
    private int endPage;
    /**
     * 게시글 총 갯수
     */
    private int total;
    /**
     * 페이지당 갯수 (default 5)
     */
    private int cntPerPage = 5;
    /**
     * 전체 마지막 페이지
     */
    private int lastPage;
    /**
     * sql 쿼리에서 사용할 start값
     */
    private int start;

    /**
     * 하단 노추 페이지 갯수
     */
    private int cntPage = 5;

    public PageCriteria() {
    }

    public PageCriteria(int total, int nowPage, int cntPerPage) {
        this.nowPage = nowPage;
        this.cntPerPage = cntPerPage;
        this.total = total;
        calcLastPage(this.total, this.cntPerPage);
        calcStartEndPage(this.nowPage, this.cntPage);
        calcStart(this.nowPage, this.cntPerPage);
    }

    // 전체의 제일 마지막 페이지 계산
    public void calcLastPage(int total, int cntPerPage) {
        setLastPage((int) Math.ceil((double) total / (double) cntPerPage));
    }

    // 시작, 끝 페이지 계산
    public void calcStartEndPage(int nowPage, int cntPage) {
        setEndPage(((int) Math.ceil((double) nowPage / (double) cntPage)) * cntPage);
        if (getLastPage() < getEndPage()) {
            setEndPage(getLastPage());
        }
        setStartPage(getEndPage() - cntPage + 1);
        if (getStartPage() < 1) {
            setStartPage(1);
        }
    }

    // DB 쿼리에서 사용할 start값 계산
    public void calcStart(int nowPage, int cntPerPage) {
        setStart((nowPage - 1) * cntPerPage);
    }
}
