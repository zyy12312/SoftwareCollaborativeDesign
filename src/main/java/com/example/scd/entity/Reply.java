package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private Integer rid;
    private String detail;
    private String fileURL;
    private Integer authorID;
    private Integer replyTarget;
    private Integer replysDiscuss;
    private LocalDateTime replyTime;
    private User author;
}
