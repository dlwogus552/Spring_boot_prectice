package com.example.sbboard2.repository.comment;

import com.example.sbboard2.dto.CommentResponseDTO;
import com.example.sbboard2.dto.QCommentResponseDTO;
import com.example.sbboard2.model.Comment;
import com.example.sbboard2.model.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommnetRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QComment comment = QComment.comment;

    @Override
    public List<CommentResponseDTO> findCommentByBoardId(Long board_id) {
        return jpaQueryFactory.select(new QCommentResponseDTO(comment))
                .from(comment)
                .where(comment.board.bno.eq(board_id))
                .orderBy(comment.root.asc(),comment.leftNum.asc())
                .fetch();
    }

    @Override
    public void updateLeftRight(Comment newComment) {
        jpaQueryFactory.update(comment)
                .set(comment.leftNum, comment.leftNum.add(2))
                .where(comment.root.eq(newComment.getRoot())
                        .and(comment.leftNum.goe(newComment.getRightNum())))
                .execute();

        jpaQueryFactory.update(comment)
                .set(comment.rightNum, comment.rightNum.add(2))
                .where(comment.root.eq(newComment.getRoot())
                        .and(comment.leftNum.goe(newComment.getRightNum())))
                .execute();
    }
}
