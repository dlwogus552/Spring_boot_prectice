package com.example.sb01.controller;

import com.example.sb01.dto.ReplyDTO;
import com.example.sb01.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;


    @GetMapping("/list/{bno}")
    public ResponseEntity<List<ReplyDTO>> getList(@PathVariable("bno") int bno) {
        List<ReplyDTO> dtoList = replyService.getList(bno);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ReplyDTO> insert(@RequestBody ReplyDTO dto){
        replyService.insert(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
