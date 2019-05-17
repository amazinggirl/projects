package com.qhit.produceReport.controller; 

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.energyConsume.dao.IEnergyConsumeDao;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import com.qhit.produceJob.service.IProduceJobService;
import com.qhit.produceReport.pojo.ProduceReport;
import com.qhit.produceReport.service.IProduceReportService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource; 
import java.util.List; 
import org.springframework.web.bind.annotation.RestController; 

/** 
* Created by GeneratorCode on 2019/04/10
*/ 

@RestController 
@RequestMapping("/produceReport") 
public class ProduceReportController {
    @Resource
    IProduceReportService  produceReportService;
    @Resource
    IBaseFlowService baseFlowService;
    @Resource
    IProduceJobService ProduceJobService;
    @Resource
    IEnergyConsumeService energyConsumeService;
    @RequestMapping("/insert") 
    public void insert(ProduceReport produceReport) { 
        produceReportService.insert(produceReport); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer reportid) { 
        produceReportService.delete(reportid); 
    } 

    @RequestMapping("/update") 
    public void update(ProduceReport produceReport) { 
        produceReportService.update(produceReport); 
    } 

    @RequestMapping("/updateSelective") 
    public List<ProduceReport> updateSelective(ProduceReport produceReport) {
        produceReportService.updateSelective(produceReport);
        return list();
    } 

    @RequestMapping("/load") 
    public ProduceReport load(Integer reportid) { 
        ProduceReport produceReport = produceReportService.findById(reportid); 
        return produceReport; 
    } 

    @RequestMapping("/list") 
    public List<ProduceReport> list()  { 
        List<ProduceReport> list = produceReportService.findsAll();
        return list; 
    } 

    @RequestMapping("/search") 
    public List<ProduceReport> search(ProduceReport produceReport) { 
        List<ProduceReport> list = produceReportService.search(produceReport); 
        return list; 
    }
    //中控
    @RequestMapping("/completeTask")
    @Transactional
    public void completeTask(ProduceReport produceReport){
        /*开发compoleteTask方法  接收参数：reportid,flowid,startjobtime,completetime*/
        //获取ID
        ProduceReport Producereport = produceReportService.findById(produceReport.getReportid());
    /*将获取的参数赋值给Producereport对象*/
        Producereport.setFlowid(produceReport.getFlowid());
        Producereport.setStartjobtime(produceReport.getStartjobtime());
        Producereport.setCompletetime(produceReport.getCompletetime());
        //通过ID获取对应的流程设备
        BaseFlow baseFlow = baseFlowService.findById(produceReport.getFlowid());
        //更改已修改的数据
        produceReportService.updateSelective(Producereport);
        ProduceJobService.inserts(Producereport,baseFlow);
        energyConsumeService.inserts(produceReport,baseFlow);



    }


} 
