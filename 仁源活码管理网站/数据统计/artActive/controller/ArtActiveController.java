package com.qhit.artActive.controller;

import com.qhit.artActive.pojo.ArtActive;
import com.qhit.artActive.service.IArtActiveService;
import com.qhit.utils.CommonUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/** 
* Created by GeneratorCode on 2019/05/10
*/ 

@RestController 
@RequestMapping("/artActive") 
public class ArtActiveController { 

    @Resource 
    IArtActiveService artActiveService; 

    @RequestMapping("/insert") 
    public void insert(ArtActive artActive) { 
        artActiveService.insert(artActive); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer acid) { 
        artActiveService.delete(acid); 
    } 

    @RequestMapping("/update") 
    public void update(ArtActive artActive) { 
        artActiveService.update(artActive); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(ArtActive artActive) { 
        artActiveService.updateSelective(artActive); 
    } 

    @RequestMapping("/load") 
    public ArtActive load(Integer acid) { 
        ArtActive artActive = artActiveService.findById(acid); 
        return artActive; 
    } 

    @RequestMapping("/list") 
    public List<ArtActive> list()  { 
        List<ArtActive> list = artActiveService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<ArtActive> search(ArtActive artActive) { 
        List<ArtActive> list = artActiveService.search(artActive); 
        return list; 
    }
    @RequestMapping("/exportToExcel")
    public void exportToExcel()throws Exception{
        String title = "文章信息表";
        String[] name = {"序号","标题 ","分类","用户","创建","状态","中心"};
        //获取数据
        Connection connection = CommonUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM art_active";
        ResultSet rs = statement.executeQuery(sql);
        CommonUtil.exportExcel(title,name,rs);

    }
} 
