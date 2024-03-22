package com.example.sbboard2.repository.search;

import com.example.sbboard2.dto.BoardListReplyCountDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.model.QBoard;
import com.example.sbboard2.model.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board=QBoard.board;
        JPQLQuery<Board> query = this.from(board);
        BooleanBuilder booleanBuilder= new BooleanBuilder();

        booleanBuilder.or(board.title.contains("1"));
        booleanBuilder.or(board.content.contains("1"));
        query.where(new Predicate[]{booleanBuilder});
        query.where(new Predicate[]{board.bno.gt(0L)});

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
        Long count=query.fetchCount();
        return new PageImpl<>(list,pageable,count);
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board=QBoard.board;
        JPQLQuery<Board> query = this.from(board);

        if(types !=null && types.length>0 && keyword!=null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            String[] var1 = types;
            int var2 = types.length;

            for(int i=0; i<var2; i++){
                switch (var1[i]) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                }
            }//end for
            query.where(new Predicate[]{booleanBuilder});
        }//end if
        query.where(board.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable,query);
        List<Board> list = query.fetch();
        Long count = query.fetchCount();
        return new PageImpl<>(list,pageable,count);
    }

    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable){
        QBoard board =QBoard.board;

        QReply reply = QReply.reply;

        JPQLQuery<Board> query = this.from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.groupBy(board);
        if(types !=null && types.length>0 && keyword !=null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            String[] var1 = types;
            int var2 = types.length;
            for(int i = 0; i<var2; i++) {
                switch (var1[i]) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(new Predicate[] {booleanBuilder});
            }
        query.where(board.bno.gt(0));
        JPQLQuery<BoardListReplyCountDTO> dtoQuery= query.select(Projections.bean(BoardListReplyCountDTO.class,
                board.bno,
                board.title,
                board.content,
                board.writer,
                board.regDate,
                reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, query);
        List<BoardListReplyCountDTO> dtoList=dtoQuery.fetch();
        Long count=dtoQuery.fetchCount();

        return new PageImpl<>(dtoList,pageable,count);
        }

    }
