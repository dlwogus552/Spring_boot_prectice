package com.example.sbboard2.dto;

import com.example.sbboard2.model.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.ToString;
import org.springframework.boot.jdbc.metadata.CommonsDbcp2DataSourcePoolMetadata;

import java.util.List;
@ToString
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String writer;
    private Long boardId;
    private Long parentId;
    private List<Comment> children;

    @QueryProjection
    public CommentResponseDTO(Comment comment){
        this.id=comment.getCno();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.boardId = comment.getBoard().getBno();
        if(comment.getParent() != null){
            this.parentId = comment.getParent().getCno();
        }
    }
}
