package com.example.sbboard2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "upload_file")
public class UploadFile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    private String filename;
    private String uuid;
    private boolean image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bno")
    private Board board;


}
