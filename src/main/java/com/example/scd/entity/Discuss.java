package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discuss {
    private Integer id;
    private String title;
    private String detail;
    private List<String> filesURL;
    private LocalDateTime discussTime;
    private Integer authorID;
}
