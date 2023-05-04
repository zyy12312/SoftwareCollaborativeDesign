package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.soap.Addressing;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer id;
    private String detail;
    private LocalDateTime sendTime;
    private Integer senderID;
    private Integer teamID;
    private User sender;
}
