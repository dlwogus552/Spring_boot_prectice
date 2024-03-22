package com.example.sbboard2.controller;

import com.example.sbboard2.dto.upload.UploadFileDTO;
import com.example.sbboard2.dto.upload.UploadResultDTO;
import com.example.sbboard2.model.UploadFile;
import com.example.sbboard2.service.UploadFileService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@Controller
@Log4j2
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final UploadFileService uploadFileService;
    @Value("${com.example.sbboard2.upload.path}")
    private String uploadPath;

    @GetMapping("/uploadForm")
    public void uploadForm(){

    }
    @PostMapping(value = "/uploadPro",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(UploadFileDTO uploadFileDTO, Model model){
        log.info(uploadFileDTO.getTitle());
        log.info(uploadFileDTO.getContent());
        log.info(uploadFileDTO.getFiles());
        if(uploadFileDTO.getFiles()!=null){
            List<UploadResultDTO> list = new ArrayList<>();
            for (MultipartFile file : uploadFileDTO.getFiles()) {
                String originalFileName = file.getOriginalFilename();
                log.info(originalFileName);
//                uploadPath=uploadPath+"\\"+getFolder();
                String uuid = UUID.randomUUID().toString();
                Path savePath= Paths.get(uploadPath, uuid+"_"+originalFileName);
                boolean image = false;

                try {
                    file.transferTo(savePath);
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image=true;
                        File thumbFile=new File(uploadPath,"s_"+uuid+"_"+originalFileName);
                        Thumbnailator.createThumbnail(savePath.toFile(),thumbFile,200,200);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                UploadFile uploadFile= UploadFile.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .image(image)
                        .build();
                Long fno = uploadFileService.upload(uploadFile);

                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .image(image)
                        .build());
            }
            model.addAttribute("fileList",list);
        }
    }
    @GetMapping("/display/{fileName}")
    public ResponseEntity<Resource> disply(@PathVariable("fileName") String fileName){
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type",Files.probeContentType(resource.getFile().toPath()));
        }catch (Exception e){e.printStackTrace();}
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
