package com.example.scd.dao.impl;

import com.example.scd.dao.MaterialDao;
import com.example.scd.entity.Information;
import com.example.scd.entity.Material;
import com.example.scd.entity.Task;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.Line;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Repository
public class MaterialDaoImpl implements MaterialDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public Integer updateInNewMaterial(Integer id) {
        return null;
    }

    @Override
    public Integer addNewMaterial(Material material) {
        Integer res = null;
        try{
            res = runner.update("insert into scd.Information(title, chapter, fileURLs, createTime, state, description,releaseTime)" +
                            " values (?,?,?,?,?,?,?)",
                    material.getTitle(),material.getChapter(),material.getFilesURL(),material.getCreateTime(),material.getState(),
                    material.getDescription(), material.getReleaseTime());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer deleteMaterial(Integer id) {
        Integer res = null;

        try{
            res = runner.update("delete from Information where id = ?",id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer modifyMaterial(Material material) {
        Integer res = null;
        try{
            res = runner.update("update Information set title = ?,chapter = ?,fileURLs = ?,createTime = ?,state = ?," +
                            "description = ?,releaseTime = ? where id = ?",
                    material.getTitle(),material.getChapter(),material.getFilesURL(),material.getCreateTime(),material.getState(),
                    material.getDescription(), material.getReleaseTime(),material.getId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer updateMaterialState(Integer materialId) {
        Integer num = 0;
        try{
            num = runner.execute("UPDATE Information SET state=1 , releaseTime = ? where id = ?",
                    LocalDateTime.now(),materialId);
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return num;
    }

    @Override
    public Material getMaterial(Integer id) {
        try{
            return runner.query("select * from Information where id = ?",new BeanHandler<Material>(Material.class),id);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Material> getMaterialList() {
        try{
            return runner.query("select * from Information",new BeanListHandler<Material>(Material.class));
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Integer addMaterialList(List<Material> materialList) {
        return null;
    }
}
