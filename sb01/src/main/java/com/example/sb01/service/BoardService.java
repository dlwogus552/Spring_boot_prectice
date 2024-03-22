package com.example.sb01.service;

import com.example.sb01.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList();
    int register(BoardDTO dto);
    int delete(int bno);
    int modify(BoardDTO dto);
    BoardDTO getBoard(int bno);

}
