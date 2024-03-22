package com.example.sbboard2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {
    private Long bno;
    private String title;
    private String writer;
    private int visitcount;
    private LocalDateTime regDate;
    private Long replyCount;
}
