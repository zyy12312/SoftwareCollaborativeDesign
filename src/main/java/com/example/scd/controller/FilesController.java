package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public Result uploadFiles(@RequestBody MultipartFile[] multipartFiles){
        if (multipartFiles != null && multipartFiles.length > 0 && !"".equals(multipartFiles[0].getOriginalFilename())) {
            List<String> fileURLs = new ArrayList<>();
            for (MultipartFile file : multipartFiles) {
                String fileName = fileUtils.fileUpload(file);
                if (fileName==null||fileName.equals("")) {
                    return Result.fail("文件名不能为空");
                }
                String url = path + fileName;
                fileURLs.add(url);
            }
            return Result.succ(fileURLs);
        }
        return  Result.fail("文件上传失败");
    }


}
