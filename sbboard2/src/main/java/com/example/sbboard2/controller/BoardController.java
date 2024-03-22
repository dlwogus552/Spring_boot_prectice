package com.example.sbboard2.controller;

import com.example.sbboard2.dto.BoardDTO;
import com.example.sbboard2.dto.BoardListReplyCountDTO;
import com.example.sbboard2.dto.PageRequestDTO;
import com.example.sbboard2.dto.PageResponseDTO;
import com.example.sbboard2.dto.upload.UploadFileDTO;
import com.example.sbboard2.dto.upload.UploadResultDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.model.UploadFile;
import com.example.sbboard2.service.BoardService;
import com.example.sbboard2.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;
    private final UploadFileService uploadFileService;
    @Value("${com.example.sbboard2.upload.path}")
    private String uploadPath;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info(pageRequestDTO);
//        PageResponseDTO<BoardDTO> responseDTO = service.listDsl(pageRequestDTO);
        PageResponseDTO<BoardListReplyCountDTO> responseDTO = service.listWidReplyCount(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);

    }


    @GetMapping("/register")
    public void register() {
    }

    @GetMapping({"/view", "/modify"})
    public void view(Long bno, Model model, PageRequestDTO pageRequestDTO) {
        model.addAttribute("fileList", uploadFileService.getList(bno));
        model.addAttribute("dto", service.getBoard(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, Model model) {
        Long bno = service.modify(boardDTO);
        if (bno != null) {
            model.addAttribute("board", service.modify(boardDTO));
            return "redirect:/board/view?bno=" + bno;
        }
        return "redirect:/board/modify?bno=" + bno;
    }


    @GetMapping("/remove")
    public String delete(Long bno) {
        service.remove(bno);
        return "redirect:/board/list";
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String register(BoardDTO boardDTO, UploadFileDTO uploadFileDTO) {
        if (uploadFileDTO.getFiles() != null) {
            final List<UploadResultDTO> list = new ArrayList<>();
            for (MultipartFile file : uploadFileDTO.getFiles()) {
                String originalFileName = file.getOriginalFilename();
                log.info(originalFileName);
//                uploadPath=uploadPath+"\\"+getFolder();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);
                boolean image = false;

                try {
                    file.transferTo(savePath);
                    if (Files.probeContentType(savePath).startsWith("image")) {
                        image = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                UploadFile uploadFile = UploadFile.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .image(image)
                        .build();
                Long fno = uploadFileService.upload(uploadFile);

                Long bno = service.register(boardDTO);
                if (bno != null) {
                    log.info(bno);
                    return "redirect:/board/list";
                }
                return "redirect:/board/register";
            }
//    @GetMapping("/writer/{writer}")
//    @ResponseBody
//    public List<Board> getWriter(@PathVariable("writer") String writer){
////        List<Board> boardList=service.getByWriter(writer);
////        return boardList;
//    }


//    @GetMapping("/list")
//    public void list(Model model, @PageableDefault(size=5,sort="bno",direction = Sort.Direction.DESC) Pageable pageable){
//        model.addAttribute("msg","list page");
//        List<BoardDTO> boardDTOList = service.getList(pageable);
//        model.addAttribute("boardList",boardDTOList);
//    }

//    @GetMapping("/content/{keyword}")
//    @ResponseBody
//    public List<Board> getKeyword(@PathVariable("keyword") String ketword) {
////        List<Board> boardList = service.getByContent(ketword);
////        return boardList;
//    }
        }
        return "redirect:/board/register";
    }
}