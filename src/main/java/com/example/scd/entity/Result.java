package com.example.scd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    //成功
    public static Result succ( Object data){

        return succ(200,"操作成功",data);
    }
    //成功
    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    //失败
    public static Result fail(String msg){
        return fail(400,msg,null);
    }
    //失败
    public static Result fail(String msg,Object data){

        return fail(400,msg,data);
    }
    //失败
    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
