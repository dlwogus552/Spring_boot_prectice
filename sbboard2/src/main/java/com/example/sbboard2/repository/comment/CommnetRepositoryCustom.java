package com.example.sbboard2.repository.comment;

import com.example.sbboard2.dto.CommentResponseDTO;
import com.example.sbboard2.model.Comment;

import java.util.List;

public interface CommnetRepositoryCustom {
    List<CommentResponseDTO> findCommentByBoardId(Long board_id);
    void updateLeftRight(Comment comment);
}
