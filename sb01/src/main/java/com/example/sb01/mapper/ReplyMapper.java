package com.example.sb01.mapper;

import com.example.sb01.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReplyMapper {
    List<ReplyDTO> getList(int bno);
    void delete(int rno);
    int insert(ReplyDTO dto);
}
