package com.example.sbboard2.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.sbboard2.dto.QCommentResponseDTO is a Querydsl Projection type for CommentResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCommentResponseDTO extends ConstructorExpression<CommentResponseDTO> {

    private static final long serialVersionUID = 191024336L;

    public QCommentResponseDTO(com.querydsl.core.types.Expression<? extends com.example.sbboard2.model.Comment> comment) {
        super(CommentResponseDTO.class, new Class<?>[]{com.example.sbboard2.model.Comment.class}, comment);
    }

}

