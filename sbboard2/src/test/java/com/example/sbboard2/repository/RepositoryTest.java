package com.example.sbboard2.repository;

import com.example.sbboard2.dto.CommentRequestDTO;
import com.example.sbboard2.dto.CommentResponseDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.service.CommentService;
import jakarta.validation.constraints.Null;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.springframework.data.domain.Sort.by;

@SpringBootTest
@Log4j2
public class RepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired

    private CommentService commentService;
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("title...."+i)
                    .content("content...."+i)
                    .writer("user"+(i%10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("bno : "+result.getBno());
        });
    }
    @Test
    public void testSelect(){
        Long bno = 123L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.get();
        log.info(board.getTitle());
    }
    @Test
    public void commentInsertTest(){
        commentService.create(new CommentRequestDTO("content2","user2",300L, 4L));
    }
    @Test
    public void commentListTest(){
        List<CommentResponseDTO> list=commentService.findAll(300L);
        list.forEach(commentResponseDTO -> {log.info(commentResponseDTO);});
    }
    @Test
    public void updateSelect() {
        Long bno = 2L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.get();
        board.change("update_title....2","update_content....2","update_writer....2");
        log.info(boardRepository.save(board));
    }
    @Test
    public void delete(){
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }


    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByTitleContainingOrderByBnoDesc("1",pageable);
        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(result.getTotalElements());
        log.info(result.getTotalPages());
        log.info(result.getNumber());
        log.info(result.getSize());
        List<Board> boardList=result.getContent();
        boardList.forEach(board -> {
            log.info(board);
        });
    }
    @Test
    public void keyword1(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByKeyword("1",pageable);
        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(result.getTotalElements());
        log.info(result.getTotalPages());
        log.info(result.getNumber());
        log.info(result.getSize());
        List<Board> boardList=result.getContent();
        boardList.forEach(board -> {
            log.info(board);
        });
    }
    @Test
    public void search1(){
        Pageable pageable=PageRequest.of(1,10,Sort.by("bno").descending());
        Page<Board> result=boardRepository.search1(pageable);
        log.info("total page:"+result.getTotalPages());
        log.info("total count:"+result.getTotalElements());
        log.info("page size:"+result.getSize());
        log.info("pageNumber:"+result.getNumber());
        log.info(result.hasPrevious()+":"+result.hasNext());
        result.getContent().forEach(board -> {
            log.info(board);
        });
    }
    @Test
    public void searchAll(){
        Pageable pageable=PageRequest.of(0,10,Sort.by("bno").descending());
        String[] types = {"t","w","c"};
        Page<Board> result=boardRepository.searchAll(types,"9",pageable);
        log.info("total page:"+result.getTotalPages());
        log.info("total count:"+result.getTotalElements());
        log.info("page size:"+result.getSize());
        log.info("pageNumber:"+result.getNumber());
        log.info(result.hasPrevious()+":"+result.hasNext());
        result.getContent().forEach(board -> {
            log.info(board);
        });
    }

}
