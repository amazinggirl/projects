package com.qhit.buseActive.controller; 

import com.qhit.adminUser.pojo.AdminUser;
import com.qhit.buseActive.pojo.BuseActive;
import com.qhit.buseActive.service.IBuseActiveService;
import com.qhit.utils.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource; 
import java.util.List; 
import org.springframework.web.bind.annotation.RestController; 

/** 
* Created by GeneratorCode on 2019/05/17
*/ 

@RestController 
@RequestMapping("/buseActive") 
public class BuseActiveController { 

    @Resource 
    IBuseActiveService buseActiveService; 

    @RequestMapping("/insert") 
    public void insert(BuseActive buseActive) { 
        buseActiveService.insert(buseActive); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer baid) { 
        buseActiveService.delete(baid); 
    } 

    @RequestMapping("/update") 
    public void update(BuseActive buseActive) { 
        buseActiveService.update(buseActive); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(BuseActive buseActive) { 
        buseActiveService.updateSelective(buseActive); 
    } 

    @RequestMapping("/load") 
    public BuseActive load(Integer baid) { 
        BuseActive buseActive = buseActiveService.findById(baid); 
        return buseActive; 
    } 

    @RequestMapping("/list") 
    public List<BuseActive> list()  { 
        List<BuseActive> list = buseActiveService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search")
    public List<BuseActive> search(BuseActive buseActive) { 
        List<BuseActive> list = buseActiveService.search(buseActive); 
        return list; 
    }
    @RequestMapping("/dianzan")
    public BuseActive dianzan(Integer acid){
        RedisUtils redisUtils = new RedisUtils();
        //redisUtils取用户信息
        AdminUser adminUser = (AdminUser)redisUtils.get("BaseUser");
        int i = adminUser.getAid();
        BuseActive buseActive = buseActiveService.dainzan(acid,i);
        //判断有没有点过赞
        if (buseActive.getBaid()!=0){
            buseActiveService.deleteByaid(i);
        }else {
            buseActive.setAid(i);
            buseActive.setAcid(acid);
            buseActiveService.insert(buseActive);
        }
        return buseActive;
    }
} 
