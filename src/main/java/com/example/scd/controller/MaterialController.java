package com.example.scd.controller;

import com.example.scd.entity.Material;
import com.example.scd.entity.Result;
import com.example.scd.entity.Task;
import com.example.scd.entity.User;
import com.example.scd.service.MaterialService;
import com.example.scd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/material")
@CrossOrigin
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    //创建资料
    @RequestMapping(value = "/createMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Result createMaterial(@RequestBody Material material){
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = materialService.teacherUpdateMaterial(material);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail("上传失败！");
        }
        return Result.succ(200, "上传成功！", null);
    }
    //编辑资料
    @RequestMapping(value = "/editMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Result editMaterial(@RequestBody Material material){
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = materialService.teacherEditMaterial(material);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail("修改失败！");
        }
        return Result.succ(200, "修改成功！", null);
    }
    //发布资料（传参数量任意）
    @RequestMapping(value = "/releaseMaterial",method = RequestMethod.POST)
    @ResponseBody
    public Result releaseMaterial(@RequestBody List<Integer> materialIDList){
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = materialService.teacherReleaseMaterial(materialIDList);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail("部分或全部发布失败！");
        }
        return Result.succ(200, "发布成功！", null);
    }
    //获取所有资料（发布、未发布）列表
    @RequestMapping(value = "/getAllMaterials",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllMaterials(){
        List<Material> materialList = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        //若是老师，可以看到已发布和未发布的作业
        try {
            materialList = materialService.getAllMaterials();
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(materialList);
    }

    //获取已发布的资料列表
    @RequestMapping(value = "/getReleasedMaterial",method = RequestMethod.GET)
    @ResponseBody
    public Result getReleasedMaterial(){
        List<Material> materialList = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        //若是老师，可以看到已发布和未发布的作业
        try {
            materialList = materialService.getAllReleasedMaterials();
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(materialList);
    }

    //删除资料（传参数量任意）
    @RequestMapping(value = "/deleteMaterial",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteMaterial(@RequestBody List<Integer> materialIDList){
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = materialService.teacherDeleteMaterial(materialIDList);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail("部分或全部删除失败！");
        }
        return Result.succ(200, "删除成功！", null);
    }

}
