package com.example.sbboard2.service;

import com.example.sbboard2.dto.PageRequestDTO;
import com.example.sbboard2.dto.PageResponseDTO;
import com.example.sbboard2.dto.ReplyDTO;

public interface ReplyService {
     Long register(ReplyDTO dto);
     ReplyDTO read(Long rno);
     void modify(ReplyDTO dto);
     void remove(Long rno);
     PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
