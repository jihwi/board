package com.hbproject.board.common.file.service;

import com.hbproject.board.common.file.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/file")
@Slf4j
/**
 * 파일 업로드/ 다운로드 핸들러
 */
public class FileHandler {

    private String[] imageExtentions = {"jpg", "png", "jpeg"};
    private String uploadFolder = "/Users/cjenm/개인폴더/files";

    /**
     * 파일 업로드
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/upload")
    public List<FileDto> uploadAjaxAction(MultipartFile[] uploadFile) throws IOException {
        //연월일 업로드 폴더 생성
        String today = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.of("Asia/Seoul")).format(Instant.now());
        File uploadPath = new File(uploadFolder, today);
        log.info("uploadPath : " + uploadPath);

        // 디렉토리 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        //첨부된 파일의 이름을 담을 List
        List<FileDto> list = new ArrayList<>();

        for (MultipartFile multipartFile : uploadFile) {

            log.info("-----------");
            log.info("파일명 : " + multipartFile.getOriginalFilename());
            log.info("파일크기 : " + multipartFile.getSize());

            File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());

            try {
                //물리적으로 파일 업로드가 됨
                multipartFile.transferTo(saveFile);
                FileDto fileDto = FileDto.builder().fileName(multipartFile.getOriginalFilename()).filePath(today)
                        .filePullPath(today+ File.separator + multipartFile.getOriginalFilename()).build();

                //-------썸네일 처리 시작---------
                //이미지 파일인지 체킹
                if (this.checkImageType(saveFile)) {
                    log.info("이미지 파일: true");
                    fileDto.setImage(true);

                    this.createThumbnail(multipartFile, uploadPath.toPath().toString());

                } else {
                    log.info("이미지 파일: false");
                }

                list.add(fileDto);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    //이미지 확장자 검사
    private boolean checkImageType(File file) {
        String extension = FilenameUtils.getExtension(file.getPath());
        log.info("extension:{}", extension);

        return Arrays.stream(this.imageExtentions).anyMatch(s -> extension.equals(s));
    }


    //이미지 썸네일 생성
    private void createThumbnail(MultipartFile multipartFile, String uploadPath) throws IOException{
        FileOutputStream thumbnail = new FileOutputStream( new File(uploadPath, "s_" + multipartFile.getOriginalFilename()));
        Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail, 100, 100);
        thumbnail.close();
    }


    /**
     * 파일 다운로드
     * @param filePath
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(@RequestParam("filePath") String filePath, HttpServletResponse response) throws IOException {
        try {
            String[] split = filePath.split("/");
            //특수문자 및 한국어 파일명 깨짐 이슈 해결
            String fileName = new String(split[split.length - 1].getBytes("UTF-8"), "ISO-8859-1");
            // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            //등록된 경로의 파일 읽어오기
            File file = new File(uploadFolder, filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            //reponse에서 outputStream을 얻어온다.
            OutputStream out = response.getOutputStream();
            BufferedInputStream input = new BufferedInputStream(fileInputStream);

            int data = 0;
            while ((data = input.read()) != -1) {
                out.write(data);
            }

            out.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
