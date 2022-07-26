package com.hbproject.board.services.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    /**
     * 아이디
     */
    @NotBlank
    private String id;
    /**
     * 비밀번호
     */
    @NotBlank
    private String pw;
    /**
     * 주소
     */
    private String address;
    /**
     * 이름
     */
    private String name;
    /**
     * 성별
     */
    private String sex;
    /**
     * 나이
     */
    private Integer age;
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

    /**
     * 사용자 권한 role
     */
    private String role;

}
