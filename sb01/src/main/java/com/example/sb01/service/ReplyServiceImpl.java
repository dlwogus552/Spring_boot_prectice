package com.example.sb01.service;

import com.example.sb01.dto.ReplyDTO;
import com.example.sb01.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyMapper replyMapper;
    @Override
    public List<ReplyDTO> getList(int bno) {
        replyMapper.getList(bno);
        return replyMapper.getList(bno);
    }

    @Override
    public int insert(ReplyDTO dto) {
        return replyMapper.insert(dto);
    }
}
