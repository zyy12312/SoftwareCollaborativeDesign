package com.example.scd.controller;

import com.example.scd.entity.Material;
import com.example.scd.entity.Result;
import com.example.scd.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/material")
@CrossOrigin
public class MeterialController {

    @Autowired
    private MaterialService materialService;

    //创建资料
    @RequestMapping(value = "/createMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Result createMaterial(@RequestBody Material material){
        return null;
    }
    //编辑资料
    @RequestMapping(value = "/editMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Result editMaterial(@RequestBody Material material){
        return null;
    }
    //发布资料（传参数量任意）
    @RequestMapping(value = "/releaseMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Result releaseMaterial(@RequestBody List<Integer> materialIDList){
        return null;
    }
    //获取所有资料（发布、未发布）列表
    @RequestMapping(value = "/getAllMaterials",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllMaterials(){
        return null;
    }
    //获取已发布的资料列表
    @RequestMapping(value = "/getReleasedMaterial",method = RequestMethod.GET)
    @ResponseBody
    public Result getReleasedMaterial(){
        return null;
    }
    //删除资料（传参数量任意）
    @RequestMapping(value = "/deleteMaterial",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteMaterial(@RequestBody List<Integer> materialIDList){
        return null;
    }

}
