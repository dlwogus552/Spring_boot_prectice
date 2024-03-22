package com.example.sbboard2.service;

import com.example.sbboard2.dto.upload.UploadResultDTO;
import com.example.sbboard2.model.Board;
import com.example.sbboard2.model.UploadFile;
import com.example.sbboard2.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UploadFileServiceImpl implements UploadFileService{
    private final UploadFileRepository uploadFileRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<UploadResultDTO> getList(Long bno) {
        List<UploadFile> uploadFileList = uploadFileRepository.findByFileList(bno);
        List<UploadResultDTO> resultDTOList = uploadFileList.stream().map(uploadFile -> modelMapper.map(uploadFile,UploadResultDTO.class)).collect(Collectors.toList());
        return resultDTOList;
    }

    @Override
    public UploadResultDTO getFile(Long fno) {
        UploadFile file = uploadFileRepository.findById(fno).get();
        UploadResultDTO dto = UploadResultDTO.builder()
                .filename(file.getFilename())
                .image(file.isImage())
                .uuid(file.getUuid())
                .build();
        return dto;
    }

    @Override
    public void delete(Long fno) {
        uploadFileRepository.deleteById(fno);
    }

    @Override
    public Long upload(UploadFile file) {
        Board board = Board.builder().bno(file.getBoard().getBno()).build();
        file.setBoard(board);
        Long fno = uploadFileRepository.save(file).getFno();
        return fno;
    }

    @Override
    public void deleteAll(Long bno) {
        }
}
