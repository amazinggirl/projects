package com.qhit.produceReport.service.impl;

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.produceReport.service.IProduceReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhit.produceReport.dao.IProduceReportDao;
import com.qhit.produceReport.pojo.ProduceReport;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 

/**
* Created by GeneratorCode on 2019/04/10
*/

@Service 
public class ProduceReportServiceImpl  implements IProduceReportService {

    @Resource 
    IProduceReportDao dao;

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
        ProduceReport produceReport = findById(id); 
        return dao.delete(produceReport); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public ProduceReport findById(Object id) { 
        List<ProduceReport> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<ProduceReport> search(ProduceReport produceReport) { 
        return dao.search(produceReport); 
    }

    @Override
    public List<ProduceReport> findsAll() {
        return dao.findsAll();
    }

    @Override
    public List<Map> model(String year, BaseFlow baseFlow) {
        //创建一个map,存入数据
        Map map=new HashMap();
        map.put("year",year);
        map.put("baseFlow",baseFlow);
        return dao.model(map);
    }

}