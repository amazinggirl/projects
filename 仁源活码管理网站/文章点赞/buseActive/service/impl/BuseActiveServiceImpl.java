package com.qhit.buseActive.service.impl;

import com.qhit.artActive.pojo.ArtActive;
import com.qhit.buseActive.service.IBuseActiveService;
import java.util.List;
import com.qhit.buseActive.dao.IBuseActiveDao;
import com.qhit.buseActive.pojo.BuseActive;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 

/**
* Created by GeneratorCode on 2019/05/17
*/

@Service 
public class BuseActiveServiceImpl  implements IBuseActiveService {

    @Resource 
    IBuseActiveDao dao;

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 

    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 

    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 

    @Override 
    public boolean delete(Object id) { 
        BuseActive buseActive = findById(id); 
        return dao.delete(buseActive); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public BuseActive findById(Object id) { 
        List<BuseActive> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<BuseActive> search(BuseActive buseActive) { 
        return dao.search(buseActive); 
    }

    @Override
    public boolean deleteByaid(int i) {
        BuseActive buseActive = findByaId(i);
        return dao.deleteByaid(buseActive);
    }

    @Override
    public BuseActive dainzan(Integer acid, int i) {
        List<BuseActive> list = dao.dainzan(acid,i);
        return list.get(0);
    }

    @Override
    public BuseActive count(Integer acid) {
        List<BuseActive> list = dao.count(acid);
        return list.get(0);
    }

    private BuseActive findByaId(int i) {
        List<BuseActive> list = dao.findByaId(i);
        return  list.get(0);
    }

}