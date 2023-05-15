package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Serializable {
    private Integer tId;
    private Integer teamID;
    private Integer studentID;
    private Integer studentCharacter;//角色编号
    private String studentName;
    private String character;//角色名称
}
