package com.example.sbboard2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
@Table(name="jpa_member")

public class MemberEntity {
    @Id
    private String username;
    private String password;
    private String phone;
}
