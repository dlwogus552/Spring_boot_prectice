package com.example.sbboard2.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@ToString(exclude="board")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="jpa_reply")
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    private String replytext;
    private String replyer;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_name")
//    private MemberEntity member;

    public void changeText(String replytext){
        this.replytext=replytext;
    }
}
