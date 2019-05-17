package com.qhit.energyConsume.controller; 

import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseCompany.service.IBaseCompanyService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import com.qhit.reports.pojo.JobAmount;
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
@RequestMapping("/energyConsume")
public class EnergyConsumeController { 

    @Resource 
    IEnergyConsumeService energyConsumeService;
    @Resource
    IBaseUserService baseUserService;
    @Resource
    IBaseCompanyService baseCompanyService;
    @Resource
    IBaseFlowService baseFlowService;

    @RequestMapping("/insert") 
    public void insert(EnergyConsume energyConsume) { 
        energyConsumeService.insert(energyConsume); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer consumeid) { 
        energyConsumeService.delete(consumeid); 
    } 

    @RequestMapping("/update") 
    public void update(EnergyConsume energyConsume) { 
        energyConsumeService.update(energyConsume); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(EnergyConsume energyConsume) { 
        energyConsumeService.updateSelective(energyConsume); 
    } 

    @RequestMapping("/load") 
    public EnergyConsume load(Integer consumeid) { 
        EnergyConsume energyConsume = energyConsumeService.findById(consumeid); 
        return energyConsume; 
    } 

    @RequestMapping("/list") 
    public List<EnergyConsume> list()  { 
        List<EnergyConsume> list = energyConsumeService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<EnergyConsume> search(EnergyConsume energyConsume) { 
        List<EnergyConsume> list = energyConsumeService.search(energyConsume); 
        return list; 
    }
    //流程能耗对比图-折标煤
    @RequestMapping("/flowConsume")
    public Map<String, Object> flowConsume(String year, HttpSession session){
    /*    BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        energyConsume.setConsumeid(baseUser.getCompid());*/
        List<EnergyConsume> energyConsumes =energyConsumeService.flowConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }
    //设备类别能耗对比图-折标煤
    @RequestMapping("/devTypeConsume")
    public Map<String, Object> devTypeConsume(String year, HttpSession session){
        List<EnergyConsume> energyConsumes = energyConsumeService.devTypeConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }
    //设备能耗对比图-折标煤
    @RequestMapping("/devConsume")
    public Map<String, Object> devConsume(String year, HttpSession session){
        List<EnergyConsume> energyConsumes = energyConsumeService.devConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }
    //设备单位作业量耗电量对比图
    @RequestMapping("/electricConsume")
    public Map<String, Object> electricConsume(String year, HttpSession session){
        List<EnergyConsume> energyConsumes = energyConsumeService.electricConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }

    //设备单位作业量耗水量对比图
    @RequestMapping("/waterConsume")
    public Map<String, Object> waterConsume(String year, HttpSession session){
        List<EnergyConsume> energyConsumes = energyConsumeService.waterConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }

    //设备单位作业量耗油量对比图
    @RequestMapping("/oilConsume")
    public Map<String, Object> oilConsume(String year, HttpSession session){
        List<EnergyConsume> energyConsumes = energyConsumeService.oilConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }
    //能源消耗模型--折标煤
    @RequestMapping("/model")
    public Map<String, Object> model(String year, HttpSession session){
        //先创建一个map ，存入对象数据
        Map map =new HashMap();
        //获取登录时存入session中的用户对象
            BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
            //获取企业ID
        Integer compid = baseUser.getCompid();
         //通过企业ID查到企业名
        BaseCompany baseCompany = baseCompanyService.findById(compid);
        //通过企业ID查询流程表中属于该企业下的流程信息
        List<BaseFlow> baseFlow = baseFlowService.findByCompid(compid);
        //定义一个企业总能耗
        double amounts=0;
        //定义一个集合存入map
        List<Map> m=new ArrayList<>();
        //通过for循环遍历该企业下的线程
        for (int i = 0; i <baseFlow.size() ; i++) {
            //先创建一个map ，存入对象数据
            Map maps = new HashMap();
            //定义一个double型，线程的总工作量
            double amount = 0;
            //查询某一年当前线程下的设备名和工作量,存入集合中
            List<Map> list = baseUserService.model(year, baseFlow.get(i));
            //通过for循环遍历该线程下的设备
            for (int j = 0; j < list.size(); j++) {
                //获取该线程下的工作量
                amount += (double) list.get(j).get("consume");
                //把该线程下的工作量存入map
                list.get(j).put("amount", list.get(j).get("consume") + "吨");
            }
            amounts += amount;
            maps.put("flow", baseFlow.get(i).getFlowname());//线程名
            maps.put("consume", amount + "吨");//线程总工作量
            maps.put("children", list);//设备信息
            m.add(maps);
        }

        map.put("comp",baseCompany.getCompname());//企业名
        map.put("consume",amounts+"吨");//总吨数
        map.put("children",m);//设备信息
        return map;


    }



} 
