package com.example.sbboard2.service;

import com.example.sbboard2.dto.CommentRequestDTO;
import com.example.sbboard2.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    void create(CommentRequestDTO commentRequestDTO);
    void delete(Long cno);
    List<CommentResponseDTO> findAll(Long boardId);
}
