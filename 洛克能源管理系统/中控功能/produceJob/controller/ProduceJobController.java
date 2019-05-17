package com.qhit.produceJob.controller; 

import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseCompany.service.IBaseCompanyService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceJob.service.IProduceJobService;
import com.qhit.produceReport.service.IProduceReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController; 

/** 
* Created by GeneratorCode on 2019/04/11
*/ 

@RestController 
@RequestMapping("/produceJob") 
public class ProduceJobController { 

    @Resource 
    IProduceJobService produceJobService;
    @Resource
    IBaseCompanyService baseCompanyService;
    @Resource
    IBaseFlowService baseFlowService;
    @Resource
    IProduceReportService produceReportService;

    @RequestMapping("/insert") 
    public void insert(ProduceJob produceJob) { 
        produceJobService.insert(produceJob); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer jobid) { 
        produceJobService.delete(jobid); 
    } 

    @RequestMapping("/update") 
    public void update(ProduceJob produceJob) { 
        produceJobService.update(produceJob); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(ProduceJob produceJob) { 
        produceJobService.updateSelective(produceJob); 
    } 

    @RequestMapping("/load") 
    public ProduceJob load(Integer jobid) { 
        ProduceJob produceJob = produceJobService.findById(jobid); 
        return produceJob; 
    } 

    @RequestMapping("/list") 
    public List<ProduceJob> list()  { 
        List<ProduceJob> list = produceJobService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<ProduceJob> search(ProduceJob produceJob) { 
        List<ProduceJob> list = produceJobService.search(produceJob); 
        return list; 
    }
    @RequestMapping("/model")
    public Map<String, Object> model(String year, HttpSession session){
        //先创建一个map ，存入对象数据
        Map map =new HashMap();
        //获取登录时存入session中的用户对象
        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        //获取用户企业Id;
        Integer compid = baseUser.getCompid();
        //通过企业ID查到企业名
        BaseCompany baseCompany = baseCompanyService.findById(compid);
        //通过企业ID查询流程表中属于该企业下的流程信息
        List<BaseFlow> baseFlow= baseFlowService.findByCompid(compid);
        //定义一个double 型的企业总工作量
        double amounts =0;
        //定义一个集合，存入map
        List<Map> m=new ArrayList<>();
        //通过for循环遍历该企业下的线程
        for (int i = 0; i <baseFlow.size() ; i++) {
            //先创建一个map ，存入对象数据
            Map maps =new HashMap();
            //定义一个double型，线程的总工作量
            double amount =0;
            //查询某一年当前线程下的设备名和工作量,存入集合中
            List< Map>list = produceReportService.model(year,baseFlow.get(i));
            //通过for循环遍历该线程下的设备
            for (int j = 0; j < list.size(); j++) {
                //获取该线程下的工作量
                amount+=(double) list.get(j).get("bfamount");
                //把该线程下的工作量存入map
                list.get(j).put("amount",list.get(j).get("bfamount")+"吨");
            }
            amounts+=amount;
            maps.put("flow",baseFlow.get(i).getFlowname());//线程名
            maps.put("amount",amount+"吨");//线程总工作量
            maps.put("children",list);//设备信息
            System.out.println(list);
            m.add(maps);
        }
        map.put("comp",baseCompany.getCompname());//企业名
        map.put("amount",amounts+"吨");//总吨数
        map.put("children",m);//设备信息
        System.out.println(m);
        return map;

    }
} 
