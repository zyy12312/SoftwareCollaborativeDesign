package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer mid;
    private String detail;
    private LocalDateTime sendTime;
    private Integer senderID;
    private Integer teamID;
    private User sender;
}
