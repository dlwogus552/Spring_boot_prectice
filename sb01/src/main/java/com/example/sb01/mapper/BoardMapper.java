package com.example.sb01.mapper;

import com.example.sb01.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
    List<BoardDTO> getList();
    int insert(BoardDTO dto);
    BoardDTO getBoard(int bno);
    int update(BoardDTO dto);
    int delete(int bno);

}
