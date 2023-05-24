package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FilesController {

    @Autowired
    FileUtils fileUtils;

    @Value("${spring.tengxun.url}")
    private String path;

    @PostMapping("/upload")
    @ResponseBody
    public Result uploadFiles(@RequestParam("file") MultipartFile[] multipartFiles) {
        List<String> fileURLs = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String fileName = fileUtils.fileUpload(file);
            if (fileName == null || fileName.equals("")) {
                return Result.fail("文件名不能为空");
            }
            String url = path +"/"+ fileName;
            fileURLs.add(url);
        }
        System.out.println(fileURLs);
        if (fileURLs.isEmpty()){
            return Result.fail("文件上传失败");
        }
        return Result.succ(fileURLs);
    }
}
