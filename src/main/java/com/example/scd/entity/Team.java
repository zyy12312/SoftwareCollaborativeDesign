package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private Integer tId;
    private Integer teamID;
    private Integer studentID;
    private Integer studentCharacter;
    private String studentName;
    private String character;
}
