package com.example.sbboard2.service;

import com.example.sbboard2.dto.upload.UploadResultDTO;
import com.example.sbboard2.model.UploadFile;

import java.util.List;

public interface UploadFileService {
    List<UploadResultDTO> getList(Long bno);
    UploadResultDTO getFile(Long fno);
    void delete(Long fno);
    Long upload(UploadFile file);
    void deleteAll(Long bno);
}
