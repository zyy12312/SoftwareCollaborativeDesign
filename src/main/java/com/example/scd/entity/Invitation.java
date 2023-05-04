package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
    private Integer id;
    private Integer inviterID;
    private Integer inviteeID;
    private Integer state;
    private Integer teamID;
    private LocalDateTime invitationTime;
    private User inviter;
}
