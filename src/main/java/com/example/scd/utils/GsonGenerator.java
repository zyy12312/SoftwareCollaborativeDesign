package com.example.scd.utils;

import com.example.scd.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GsonGenerator {
    public static Gson gsonSetter(){
        //序列化
        final JsonSerializer<LocalDateTime> jsonSerializerDateTime = (localDateTime, type, jsonSerializationContext)
                -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        final  JsonSerializer<LocalDate> jsonSerializerDate = (localDate, type, jsonSerializationContext)
                -> new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //反序列化
        final JsonDeserializer<LocalDateTime> jsonDeserializerDateTime = (jsonElement, type, jsonDeserializationContext)
                -> LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        final  JsonDeserializer<LocalDate> jsonDeserializerDate = (jsonElement, type, jsonDeserializationContext)
                -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                /* 更改先后顺序没有影响 */
                .registerTypeAdapter(LocalDateTime.class, jsonSerializerDateTime)
                .registerTypeAdapter(LocalDate.class, jsonSerializerDate)
                .registerTypeAdapter(LocalDateTime.class, jsonDeserializerDateTime)
                .registerTypeAdapter(LocalDate.class, jsonDeserializerDate)
                .create();
    }

    public static void main(String[] args) {
        Gson gson = GsonGenerator.gsonSetter();
        String task = gson.toJson(new Task(null,"需求分析报告","撰写需求分析报告",LocalDateTime.now(),2,"产品经理",null,0));
        String message = gson.toJson(new Message(null, "111", LocalDateTime.now(), 1, 1, null));
        String discuss = gson.toJson(new Discuss(null, "讨论测试","测试讨论功能",null, LocalDateTime.now(), 1));
        String subtask = gson.toJson(new Subtask(null, 1, 3, "开发经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        List<Subtask> subtaskList = new ArrayList<>();
        subtaskList.add(new Subtask(null, 1, 3, "开发经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        subtaskList.add(new Subtask(null, 1, 2, "产品经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        subtaskList.add(new Subtask(null, 1, 1, "组长", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        String team = gson.toJson(new Team(null, 1, 12, 5, null, null));
        String s2 = gson.toJson(subtaskList);
        String material = gson.toJson(new Material(null,"资料1","第一个资料",null,0,null,null,LocalDateTime.now()));
        String reply1 = gson.toJson(new Reply(null, "测试发布回复1", null, 13, 1, 0, LocalDateTime.now(), null));
        System.out.println(reply1);
        String submission = gson.toJson(new Submission(null, 9, 1, "提交了我的想法", null, 2, 1, null, LocalDateTime.now(), null, null));
        String invitation = gson.toJson(new Invitation(null, 8, 8, null, LocalDateTime.now(), 2, 2, null));
        System.out.println(invitation);

    }

}
