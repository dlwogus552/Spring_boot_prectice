package com.example.sbboard2.model;

import com.example.sbboard2.dto.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 100)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    private Long root;
    private Long leftNum=1L;
    private Long rightNum=2L;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @Builder
    public Comment(CommentRequestDTO commentRequestDTO){
        this.content=commentRequestDTO.getContent();
        this.writer=commentRequestDTO.getWriter();
    }

    public void setChild(Comment child){
        child.root=this.root;
        child.parent = this;
        child.leftNum= this.leftNum+1;
        child.rightNum= this.leftNum+1;
        this.children.add(child);
    }
    public void setRootId(Comment comment){
        this.root = comment.getCno();
    }

}
