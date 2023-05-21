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
        String s = gson.toJson(new Task(null,"需求分析报告","撰写需求分析报告",LocalDateTime.now(),2,"产品经理",null,0));
        String s1 = gson.toJson(new User(2, "2011110101", "123456", "张张", "https://cos-for-scd-1312783961.cos.ap-shanghai.myqcloud.com/defaultAvator.png", 0, 0, 1, null, true));
        String message = gson.toJson(new Message(null, "111", LocalDateTime.now(), 1, 1, null));
        String discuss = gson.toJson(new Discuss(null, "讨论测试","测试讨论功能",null, LocalDateTime.now(), 1));
        String subtask = gson.toJson(new Subtask(null, 1, 3, "开发经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        List<Subtask> subtaskList = new ArrayList<>();
        subtaskList.add(new Subtask(null, 1, 3, "开发经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        subtaskList.add(new Subtask(null, 1, 2, "产品经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        subtaskList.add(new Subtask(null, 1, 1, "组长", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        String s2 = gson.toJson(subtaskList);

        System.out.println(s2);
    }

}
