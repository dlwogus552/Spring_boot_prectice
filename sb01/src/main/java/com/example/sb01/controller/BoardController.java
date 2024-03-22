package com.example.sb01.controller;

import com.example.sb01.dto.BoardDTO;
import com.example.sb01.mapper.BoardMapper;
import com.example.sb01.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("boardList",boardService.getList());
    }

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String registerPro(BoardDTO dto, RedirectAttributes redirectAttributes){
        int result = boardService.register(dto);
        if(result==1){
            return "redirect:/board/list";
        }else{
            redirectAttributes.addFlashAttribute("error","글쓰기 실패");
            return "redirect:/board/register";
        }
    }
    @GetMapping({"/view","/edit"})
    public void view(@RequestParam("bno") int bno, Model model){
        model.addAttribute("boardDTO",boardService.getBoard(bno));
    }
    @PostMapping("/edit")
    public String edit(BoardDTO dto,RedirectAttributes redirectAttributes){
        int result=boardService.modify(dto);
        if(result==1){
            return "redirect:/board/view?bno="+dto.getBno();
        }else{
            redirectAttributes.addFlashAttribute("error","수정 실패");
            return "redirect:/board/edit?bno="+dto.getBno();
        }
    }
    @GetMapping("/remove")
    public String delete(@RequestParam("bno") int bno,RedirectAttributes redirectAttributes){
        int result=boardService.delete(bno);
        if(result==1){
            return "redirect:/board/list";
        }else{
            redirectAttributes.addFlashAttribute("error","삭제 실패");
            return "redirect:/board/view?bno="+bno;
        }
    }
}
