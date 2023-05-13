package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private Integer id;
    private String title;
    private String description;
    private Integer chapter;
    private Integer state;
    private List<String> filesURL;
    private LocalDateTime releaseTime;
    private LocalDateTime createTime;
}
