package com.example.scd.service.impl;

import com.example.scd.config.MyPasswordEncoder;
import com.example.scd.dao.AccountDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    AccountDao userDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByAccount(s);
        if(user == null){
            return new User();
        }
        List<Team> teamList = teamDao.getTeamByStudentId(user.getId());
        if(teamList.size() != 0){
            user.setTeam(teamList.get(0));
        }
        return user;
    }

    /**
     * @param user
     * @return 0表示成功
     * 1表示用户名重复
     * 2表示失败
     */
    @Override
    public Integer addUser(User user) {
        User loadUserByUsername = userDao.getUserByAccount(user.getAccount());
        if (loadUserByUsername != null) {
            return 1;
        }
        //插入用户,插入之前先对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);//用户可用
        Integer result = userDao.addUser(user);
        if (result == 1) {
            return 0;
        } else {
            return 2;
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllStudent() {
        return userDao.getAllStudent();
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public Integer updateUser(User user) {
        User userByAccount = userDao.getUserByAccount(user.getAccount());
        user.setPassword(userByAccount.getPassword());
        return userDao.updateStudent(user);
    }

}
