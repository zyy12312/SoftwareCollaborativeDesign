package com.example.scd.utils;

import com.google.gson.Gson;

import java.time.format.DateTimeFormatter;

public class StaticUtil {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static Gson gson = GsonGenerator.gsonSetter();

}
