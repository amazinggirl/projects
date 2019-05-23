package com.qhit.artActive.service.impl;

import com.qhit.artActive.dao.IArtActiveDao;
import com.qhit.artActive.pojo.ArtActive;
import com.qhit.artActive.service.IArtActiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.AbstractDocument;
import java.util.List;

/**
* Created by GeneratorCode on 2019/05/10
*/

@Service 
public class ArtActiveServiceImpl  implements IArtActiveService {

    @Resource 
    IArtActiveDao dao;

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
        ArtActive artActive = findById(id); 
        return dao.delete(artActive); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public ArtActive findById(Object id) { 
        List<ArtActive> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<ArtActive> search(ArtActive artActive) { 
        return dao.search(artActive); 
    }


}