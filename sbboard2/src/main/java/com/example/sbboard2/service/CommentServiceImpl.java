package com.example.sbboard2.service;

import com.example.sbboard2.dto.CommentRequestDTO;
import com.example.sbboard2.dto.CommentResponseDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.model.Comment;
import com.example.sbboard2.repository.BoardRepository;
import com.example.sbboard2.repository.CommentRepository;
import com.example.sbboard2.repository.comment.CommentRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{
    private final CommentRepositoryCustomImpl commentRepositoryCustom;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    @Transactional
    @Override
    public void create(CommentRequestDTO commentRequestDTO) {
        Comment comment = new Comment(commentRequestDTO);

        Optional<Board> result = boardRepository.findById(commentRequestDTO.getBoardId());
        if(result !=null){
            Board board = result.get();
            comment.setBoard(board);

        }
        if(commentRequestDTO.getParentId() !=null){
            Optional<Comment> parent = commentRepository.findById(commentRequestDTO.getParentId());

            if(parent.isPresent()){
                parent.get().setChild(comment);
                commentRepositoryCustom.updateLeftRight(comment);
                commentRepository.save(comment);
            }
        }else{
            commentRepository.save(comment);
            comment.setRootId(comment);
        }
    }

    @Override
    public void delete(Long cno) {
        Optional<Comment> comment = commentRepository.findById(cno);
        if(comment.isPresent()){
            commentRepository.deleteById(cno);
        }
    }

    @Override
    public List<CommentResponseDTO> findAll(Long boardId) {
        return commentRepositoryCustom.findCommentByBoardId(boardId);
    }
}
