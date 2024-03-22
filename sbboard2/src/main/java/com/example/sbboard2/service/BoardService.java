package com.example.sbboard2.service;

import com.example.sbboard2.dto.BoardDTO;
import com.example.sbboard2.dto.BoardListReplyCountDTO;
import com.example.sbboard2.dto.PageRequestDTO;
import com.example.sbboard2.dto.PageResponseDTO;
import com.example.sbboard2.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    List<BoardDTO> getList(Pageable pageable);
    Long modify(BoardDTO boardDTO);
    void remove(Long bno);
    BoardDTO getBoard(Long bno);
    List<BoardDTO> getByWriter(String name);
    List<BoardDTO> getByContent(String keyword);
    PageResponseDTO<BoardDTO> listDsl(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> listWidReplyCount(PageRequestDTO pageRequestDTO);
}
