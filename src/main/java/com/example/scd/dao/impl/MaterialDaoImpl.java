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
        try{
            runner.update("insert into Information(title, chapter, fileURLs, createTime, state, description, releaseTime)" +
                            " values (?,?,?,?,?,?,?)",
                    material.getTitle(),material.getChapter(),material.getFilesURL(),material.getCreateTime(),material.getState(),
                    material.getDescription(), material.getReleaseTime());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Integer deleteMaterial(Integer id) {
        try{
            runner.update("delete from Information where id = ?",id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Integer modifyMaterial(Material material) {
        try{
            runner.update("update Information set title = ?,chapter = ?,fileURLs = ?,createTime = ?,state = ?," +
                            "description = ?,releaseTime = ? where id = ?",
                    material.getTitle(),material.getChapter(),material.getFilesURL(),material.getCreateTime(),material.getState(),
                    material.getDescription(), material.getReleaseTime(),material.getId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
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
