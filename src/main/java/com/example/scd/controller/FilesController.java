package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/upload")
    @ResponseBody
    public Result uploadFiles(@RequestBody MultipartFile[] multipartFiles){
        if (multipartFiles != null && multipartFiles.length > 0 && !"".equals(multipartFiles[0].getOriginalFilename())) {
            List<String> fileURLs = new ArrayList<>();
            for (MultipartFile file : multipartFiles) {

                String fileName = fileUtils.fileUpload(file);
                if (StringUtils.isBlank(fileName)) {
                    return ResultUtil.fail("图片上传异常");
                }
                String url = path + fileName;
                fileNameList.add(fileName);
                Image image = new Image();
                image.setImageType(HELP.ordinal());
                image.setImageSrcId(help.getId());
                image.setImageUrl(url);
                boolean save1 = imageService.save(image);

                if (!save1) {
                    return ResultUtil.fail(ERROR_SAVE_IMAGE, "图片保存失败");
                }
            }
        }
        return  null;
    }


}
