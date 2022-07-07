package com.hbproject.board.services.sample.service;

import com.hbproject.board.services.sample.dto.Sample;
import com.hbproject.board.services.sample.mapper.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public List<Sample> getAllList(){
        return sampleRepository.selectAllList();
    }
}
