package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer id;
    private String title;
    private String detail;
    private LocalDateTime endTime;
    private Integer characterType;
    private String characterLabel;
    private String fileURL;
    private Integer state;
}
