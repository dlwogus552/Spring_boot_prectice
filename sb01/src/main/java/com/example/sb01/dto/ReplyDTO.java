package com.example.sb01.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
    private int rno;
    private int bno;
    private String reply;
    private String replyer;
    private Date postdate;
}
