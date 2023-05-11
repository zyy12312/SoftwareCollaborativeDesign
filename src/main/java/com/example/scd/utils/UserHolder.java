package com.example.scd.utils;


import com.example.scd.entity.User;


public class UserHolder {
    private static ThreadLocal<User> userHolder = new ThreadLocal<>();
    public static void setUser(User user){
        userHolder.set(user);
    }
    public static User getUser(){
        return userHolder.get();
    }
    public static void clear(){
        userHolder.remove();
    }
}
