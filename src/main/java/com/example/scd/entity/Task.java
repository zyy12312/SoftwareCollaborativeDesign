package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer taskId;
    private String title;
    private String detail;
    private LocalDateTime endTime;
    private Integer characterType;
    private String fileURL;
    private Integer state;
    private Character character;
}
