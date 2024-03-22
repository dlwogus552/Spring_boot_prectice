package com.example.sbboard2.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;
    @NotEmpty
    private Long bno;
    @NotEmpty
    private String replytext;
    @NotEmpty
    private String replyer;
    private LocalDateTime regDate,modDate;
}
