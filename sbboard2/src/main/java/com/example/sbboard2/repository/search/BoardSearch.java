package com.example.sbboard2.repository.search;

import com.example.sbboard2.dto.BoardListReplyCountDTO;
import com.example.sbboard2.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);
    Page<Board> searchAll(String[] types,String keyword,Pageable pageable);
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
