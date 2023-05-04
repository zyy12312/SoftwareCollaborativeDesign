package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private Integer id;
    private String title;
    private Integer chapter;
    private String fileURLS;
    private LocalDateTime createTime;
    private LocalDateTime releaseTime;
    private Integer state;
    private String description;
}
