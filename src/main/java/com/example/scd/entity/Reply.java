package com.example.scd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply implements Serializable {
    private Integer rid;
    private String detail;
    private String fileURL;
    private Integer authorID;
    private Integer replyTarget;
    private Integer replyIsDiscuss;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime replyTime;
    private User author;
}
