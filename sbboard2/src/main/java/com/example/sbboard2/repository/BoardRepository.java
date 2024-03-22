package com.example.sbboard2.repository;

import com.example.sbboard2.model.Board;
import com.example.sbboard2.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);
    @Query("select b from Board b where :type like concat('%',:keyword,'%') order by b.bno desc")
    Page<Board> findByKeyword(String keyword,Pageable pageable);
}
