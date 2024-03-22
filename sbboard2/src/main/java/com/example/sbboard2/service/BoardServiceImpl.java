package com.example.sbboard2.service;

import com.example.sbboard2.dto.BoardDTO;
import com.example.sbboard2.dto.BoardListReplyCountDTO;
import com.example.sbboard2.dto.PageRequestDTO;
import com.example.sbboard2.dto.PageResponseDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.repository.BoardRepository;
import com.example.sbboard2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        log.info("service : "+board);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    public List<BoardDTO> getList(Pageable pageable){
        Page<Board> result = boardRepository.findAll(pageable);
        List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public Long modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter());
        return boardRepository.save(board).getBno();
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }
    @Override
    public BoardDTO getBoard(Long bno) {
        Board result = boardRepository.findById(bno).get();
        result.updateVisitcount();
        BoardDTO boardDTO = modelMapper.map(result,BoardDTO.class);
        return boardDTO;
    }

    @Override
    public List<BoardDTO> getByWriter(String writer) {
//        boardRepository.findByWriter(writer);
        return null;
    }

    @Override
    public List<BoardDTO> getByContent(String keyword) {
//        boardRepository.findByContent(keyword);
        return null;
    }

    @Override
    public PageResponseDTO<BoardDTO> listDsl(PageRequestDTO pageRequestDTO) {
        String[] types=pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");
        Page<Board> result=boardRepository.searchAll(types,keyword,pageable);
//        int count = replyRepository.listOfBoard(bno)

        List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board,BoardDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> listWidReplyCount(PageRequestDTO pageRequestDTO) {
        String[] types=pageRequestDTO.getTypes();
        String keyword=pageRequestDTO.getKeyword();
        Pageable pageable=pageRequestDTO.getPageable("bno");
//        Page<Board> result =boardRepository.searchAll(types,keyword,pageable);
        Page<BoardListReplyCountDTO> result=boardRepository.searchWithReplyCount(types,keyword,pageable);

        return PageResponseDTO.<BoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();

    }
}
