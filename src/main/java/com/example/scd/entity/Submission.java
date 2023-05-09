package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    private Integer sid;
    private Integer submitterID;
    private Integer teamID;
    private String detail;
    private String fileURL;
    private Integer targetID;
    private Integer targetType;
    private Float score;
    private LocalDateTime submitTime;
    private String comment;
    private User submitter;
}
