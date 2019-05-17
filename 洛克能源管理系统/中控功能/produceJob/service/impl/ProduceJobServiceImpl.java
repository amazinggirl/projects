package com.qhit.produceJob.service.impl;

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.produceJob.service.IProduceJobService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.qhit.produceJob.dao.IProduceJobDao;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceReport.pojo.ProduceReport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by GeneratorCode on 2019/04/11
*/

@Service 
public class ProduceJobServiceImpl  implements IProduceJobService {

    @Resource 
    IProduceJobDao dao;

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
        ProduceJob produceJob = findById(id); 
        return dao.delete(produceJob); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public ProduceJob findById(Object id) { 
        List<ProduceJob> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<ProduceJob> search(ProduceJob produceJob) { 
        return dao.search(produceJob); 
    }
@Transactional   //把此方法交给spring处理
    @Override
    public boolean inserts(ProduceReport producereport, BaseFlow baseFlow) {
        //获取实际到港时间
        SimpleDateFormat startjobtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   //获取完成时间
        SimpleDateFormat completetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        double sortime;
        try {
            long time = completetime.parse(producereport.getCompletetime()).getTime() - startjobtime.parse(producereport.getPlanjobtime()).getTime();
            sortime=(double) time/(1000*60*60);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        //设置保留的小数,保留两位小数
        DecimalFormat decimalFormat=new DecimalFormat("#.##");
        //存取数据
        ProduceJob produceJob=new ProduceJob();
        ProduceJob produceJob1=new ProduceJob();
        ProduceJob produceJob2=new ProduceJob();
        //载重量
        // valueOf() 方法用于返回给定参数的原生 Number 对象值，参数可以是原生数据类型, String等
        double f=Double.valueOf(String.valueOf(producereport.getCapacity()))/ 10;
        //斗轮机
        //把流程表中的斗轮机的id存入作业信息表
        produceJob.setDevid(baseFlow.getDljid());
        produceJob.setCompletetime(producereport.getStartjobtime());
        produceJob.setCompletetime(producereport.getCompletetime());
        produceJob.setDuration(Double.valueOf(decimalFormat.format(sortime)));
        produceJob.setAmount(f*4);
        produceJob.setReportid(producereport.getReportid());
        //装船机
        produceJob1.setDevid(baseFlow.getZcjid());
        produceJob1.setStarttime(producereport.getStartjobtime());
        produceJob1.setCompletetime(producereport.getCompletetime());
        produceJob1.setDuration(Double.valueOf(decimalFormat.format(sortime)));
        produceJob1.setAmount(f*4);
        produceJob1.setReportid(producereport.getReportid());
        //皮带机
        produceJob2.setDevid(baseFlow.getZcjid());
        produceJob2.setStarttime(producereport.getStartjobtime());
        produceJob2.setCompletetime(producereport.getCompletetime());
        produceJob2.setDuration(Double.valueOf(decimalFormat.format(sortime)));
        produceJob2.setAmount(f*4);
        produceJob2.setReportid(producereport.getReportid());
        dao.insert(produceJob);
        dao.insert(produceJob1);
        dao.insert(produceJob2);
        return true;


    }

}