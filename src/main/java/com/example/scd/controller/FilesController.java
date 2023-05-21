package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FilesController {

    @Autowired
    FileUtils fileUtils;

    @PostMapping("/upload")
    @ResponseBody
    public Result uploadFiles(){
        return  null;
    }


}
