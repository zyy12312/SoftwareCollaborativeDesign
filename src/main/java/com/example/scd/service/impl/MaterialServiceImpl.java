package com.example.scd.service.impl;

import com.example.scd.dao.MaterialDao;
import com.example.scd.entity.Material;
import com.example.scd.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialDao materialDao;
    @Override
    public Integer teacherUpdateMaterial(Material material) {//教师上传资料
        if (material!=null){
            return materialDao.addNewMaterial(material);
        }
        return 0;
    }

    @Override
    public Integer teacherReleaseMaterial(List<Integer> materialIDList) {
        Integer result = 0;
        for (Integer materialID:
                materialIDList) {
            result += materialDao.updateMaterialState(materialID);
        }
        if(result == materialIDList.size()){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer teacherDeleteMaterial(List<Integer> materialIDList) {//教师删除资料
        Integer result = 0;
        for (Integer materialID:
                materialIDList) {
            result += materialDao.deleteMaterial(materialID);
        }
        if(result == materialIDList.size()){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer teacherEditMaterial(Material material) {//教师编辑资料
        if (material!=null){
            return materialDao.modifyMaterial(material);
        }
        return 0;
    }

    @Override
    public List<Material> studentReceiveMaterials() {//学生接收（已发布的）资料
        //原方法返回Integer，功能不明，现与下方getAllReleasedMeterials方法功能重复
        return materialDao.getMaterialList().stream().filter((x)->{
            return x.getState()==1;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Material> getAllMaterials() {//获取所有资料（教师）
        return materialDao.getMaterialList();
    }

    @Override
    public List<Material> getAllReleasedMaterials() {////获取（已发布的）资料
        return materialDao.getMaterialList().stream().filter((x)->{
            return x.getState()==1;
        }).collect(Collectors.toList());
    }

}
