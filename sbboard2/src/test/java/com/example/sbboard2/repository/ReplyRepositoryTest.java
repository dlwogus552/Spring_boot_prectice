package com.example.sbboard2.repository;

import com.example.sbboard2.dto.BoardListReplyCountDTO;
import com.example.sbboard2.dto.PageRequestDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.model.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void testInsert(){
        Long bno=201L;
        Board board=Board.builder().bno(bno).build();
        Reply reply= Reply.builder()
                .board(board)
                .replyer("댓글작성자")
                .replytext("댓글내용")
                .build();
        log.info(replyRepository.save(reply));
    }
    @Test
    public void select(){
        Long bno=201L;
        Pageable pageable = PageRequest.of(0,10,Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        result.forEach(reply -> {
            log.info(reply);
        });
    }
    @Test
    public void testSelectReplyCount(){
        String[] types={"t","w","c"};
        String keyword = "8";
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);
        log.info(result.getTotalElements());
        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info(result.hasPrevious() +":"+result.hasNext());
        result.getContent().forEach(board ->{
            log.info(board);
        });
    }
}
