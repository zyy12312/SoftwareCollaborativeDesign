package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Serializable {
    private Integer id;
    private Integer teamID;
    private Integer studentID;
    private Integer studentCharacter;
    private String studentCharacterLabel;
    private User student;
}
