package com.example.scd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission implements Serializable {
    private Integer sid;
    private Integer submitterID;
    private Integer teamID;
    private String detail;
    private String fileURL;
    private Integer targetID;
    private Integer targetType;
    private Double score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;
    private String comment;
    private User submitter;
}
