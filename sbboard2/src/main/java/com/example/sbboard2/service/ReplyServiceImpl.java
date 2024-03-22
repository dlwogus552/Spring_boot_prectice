package com.example.sbboard2.service;

import com.example.sbboard2.dto.PageRequestDTO;
import com.example.sbboard2.dto.PageResponseDTO;
import com.example.sbboard2.dto.ReplyDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.model.Reply;
import com.example.sbboard2.repository.BoardRepository;
import com.example.sbboard2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(ReplyDTO dto) {
        Reply reply = modelMapper.map(dto,Reply.class);
        Board board = boardRepository.findById(dto.getBno()).get();
        board.updateReplycount();
        reply.setBoard(board);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.get();
        ReplyDTO dto = modelMapper.map(reply,ReplyDTO.class);
        return dto;
    }

    @Override
    public void modify(ReplyDTO dto) {
        Reply reply = replyRepository.findById(dto.getRno()).get();
        reply.changeText(dto.getReplytext());
        replyRepository.save(reply);
    }

    public void remove(Long rno){
        replyRepository.deleteById(rno);
    }

    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO){
        Pageable pageable= PageRequest.of(
                pageRequestDTO.getPage()<=0 ? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(), Sort.by("rno").ascending());
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply,ReplyDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
