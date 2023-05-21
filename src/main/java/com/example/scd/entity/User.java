package com.example.scd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString(includeFieldNames = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, UserDetails {
    private Integer id;
    private String account;  //用户账号
    private String password;  //'用户密码，MD5加密的字符串'
    private String name;  //用户名称
    private String avatarURL;  //'用户头像URL'
    private Integer role;  //'用户角色，0:学生；1:教师'
    private Integer sex;  //'用户性别，0:男；1:女；空：未知'
    private Integer teamId;  //'用户组号'
    private Team team;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { List<GrantedAuthority> authorities = new ArrayList<>();
       if(role == 0){
           authorities.add(new SimpleGrantedAuthority("ROLE_" + "学生"));
       }else {
           authorities.add(new SimpleGrantedAuthority("ROLE_" + "教师"));
       }
        return authorities;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
