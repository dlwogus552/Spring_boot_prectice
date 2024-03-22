package com.example.sb01.service;

import com.example.sb01.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
    List<ReplyDTO> getList(int bno);
    int insert(ReplyDTO dto);
}
