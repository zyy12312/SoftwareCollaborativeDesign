package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String account;  //用户账号
    private String password;  //'用户密码，MD5加密的字符串'
    private String name;  //用户名称
    private String avatarURL;  //'用户头像URL'
    private Integer role;  //'用户角色，0:学生；1:教师'
    private Integer sex;  //'用户性别，0:男；1:女；空：未知'
    private Integer teamId;  //'用户组号'
//    private Team team;
}
