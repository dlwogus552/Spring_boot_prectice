package com.example.sbboard2.repository;

import com.example.sbboard2.model.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    @Query("select f from UploadFile f where f.board.bno=:bno")
    List<UploadFile> findByFileList(Long bno);
}
