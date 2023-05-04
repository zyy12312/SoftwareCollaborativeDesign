package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private Integer id;
    private Integer teamID;
    private Integer studentID;
    private Integer studentCharacter;
    private User student;
    private Character character;
}
