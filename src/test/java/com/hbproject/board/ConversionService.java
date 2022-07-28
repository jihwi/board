package com.hbproject.board;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConversionService {

    @Autowired
    ConversionService conversionService;

    @Test
    public void test(){
      System.out.println(conversionService.getClass().toString());
    }
}
