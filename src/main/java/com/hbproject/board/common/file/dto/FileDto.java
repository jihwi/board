package com.hbproject.board.common.file.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class FileDto {
    private String filePath;
    private String fileName;
    private String filePullPath;
    private boolean image;
}
