package com.hbproject.board.services.sample.mapper;

import com.hbproject.board.services.sample.dto.Sample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository {
    List<Sample> selectAllList();
}
