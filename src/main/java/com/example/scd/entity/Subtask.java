package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subtask {
    private Integer stId;
    private Integer teamID;
    private Integer characterType;
    private String detail;
    private String filesURL;
    private LocalDateTime endTime;
    private Integer targetID;
    private User submitter;
}
