package com.example.sbboard.service;

import com.example.sbboard.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Board register(Board board);
    Page<Board> getList(Pageable pageable);
    Board modify(Board board);
    void remove(Long bno);
    Board visitcount(Long bno);
    Long totalcount();
    Board getBoard(Long bno);
    List<Board> getByWriter(String name);
    List<Board> getByContent(String keyword);

}
