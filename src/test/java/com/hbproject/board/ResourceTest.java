package com.hbproject.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static java.lang.System.out;

@SpringBootTest
public class ResourceTest {
    @Autowired
    private ResourceLoader resourceLoader;

    // resourceLoader 빈을 이용하지말고 그냥 new 생성자로 만들어서 사용하자.
    @Test
    public void test() throws IOException {

        out.println(resourceLoader.getClass());
        //classpath는 프로젝트경로/src/main/resources 혹은 프로젝트경로/src/main/java
//        Resource resource = resourceLoader.getResource("classpath:static/js/comment.js");
//        Resource resource = resourceLoader.getResource("file:///Users/cjenm/개인폴더/board/src/main/resources/static/test.txt");
//        Resource resource = new FileSystemResource("/Users/cjenm/개인폴더/board/src/main/resources/static/test.txt");
        Resource resource = new UrlResource("http://localhost:8080/tomcat/20220727/test.txt");
        out.println(resource.getClass());
        if (resource.exists()) {
            InputStream inputStream = resource.getInputStream();
            //inputstream -> inputstreamreader (string으로 읽기 위함)
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bis = new BufferedReader(reader);
            Stream<String> lines = bis.lines();
            lines.forEach(out::println);
        }
    }
}
