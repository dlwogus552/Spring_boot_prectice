package com.example.sbboard.controller;

import com.example.sbboard.model.Board;
import com.example.sbboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @GetMapping("/register")
    public void register(){}
    @GetMapping({"/view","/modify"})
    public void view(Long bno,Model model){
        service.visitcount(bno);
        model.addAttribute("board",service.getBoard(bno));
    }
    @PostMapping("/modify")
    public String modify(Board board,Model model) {
        Board board1=service.modify(board);
        if(board1!=null) {
            model.addAttribute("board", service.modify(board));
            return "redirect:/board/view?bno=" + board.getBno();
        }
        return "redirect:/board/modify?bno="+board.getBno();
    }


    @GetMapping("/remove")
    public String delete(Long bno){
        service.remove(bno);
        return "redirect:/board/list";
    }

    @PostMapping("register")
    public String register(Board board){
        Board board1 = service.register(board);
        if(board1 !=null){
            return "redirect:/board/list";
        }
        return "redirect:/board/register";
    }
    @GetMapping("/writer/{writer}")
    @ResponseBody
    public List<Board> getWriter(@PathVariable("writer") String writer){
        List<Board> boardList=service.getByWriter(writer);
        return boardList;
    }


    @GetMapping("/list")
    public void list(Model model, @PageableDefault(size=5,sort="bno",direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("totalcount",service.totalcount());
        model.addAttribute("msg","list page");
        Page<Board> boardList = service.getList(pageable);
        model.addAttribute("boardList",boardList);
    }

    @GetMapping("/content/{keyword}")
    @ResponseBody
    public List<Board> getKeyword(@PathVariable("keyword") String ketword) {
        List<Board> boardList = service.getByContent(ketword);
        return boardList;
    }
}
