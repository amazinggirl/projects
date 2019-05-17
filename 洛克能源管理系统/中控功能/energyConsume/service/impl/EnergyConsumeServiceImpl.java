package com.qhit.energyConsume.service.impl;

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.energyConsume.service.IEnergyConsumeService;

import java.text.DecimalFormat;
import java.util.*;

import com.qhit.energyConsume.dao.IEnergyConsumeDao;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.produceReport.pojo.ProduceReport;
import com.qhit.reports.pojo.JobAmount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by GeneratorCode on 2019/04/11
*/

@Service 
public class EnergyConsumeServiceImpl  implements IEnergyConsumeService {

    @Resource 
    IEnergyConsumeDao dao;

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
        EnergyConsume energyConsume = findById(id); 
        return dao.delete(energyConsume); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public EnergyConsume findById(Object id) { 
        List<EnergyConsume> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<EnergyConsume> search(EnergyConsume energyConsume) { 
        return dao.search(energyConsume); 
    }

    //记录耗能
    @Transactional
    @Override
    public boolean inserts(ProduceReport producereport, BaseFlow baseFlow) {
        //第一随机数利于产生随机数
        Random random=new Random();
        //设置保留的小数
        DecimalFormat df=new DecimalFormat("#.##");
        //工作量
       double f= Double.valueOf(String.valueOf(producereport.getCapacity()))/10;

       //插入数据
        //声明变量
        EnergyConsume energyConsume=new EnergyConsume();
        EnergyConsume energyConsume1=new EnergyConsume();
        EnergyConsume energyConsume2=new EnergyConsume();
        //斗轮机
        energyConsume.setDevid(baseFlow.getDljid());
        energyConsume.setElectric(Double.valueOf(df.format((random.nextInt(201)+100)*f*4)));
        energyConsume.setWater(Double.valueOf(df.format((random.nextInt(10)+1)*f*4)));
        energyConsume.setOil(Double.valueOf(df.format((random.nextInt(31)+10)*f*4)));
        energyConsume.setReportid(producereport.getReportid());
      //装船机
        energyConsume1.setDevid(baseFlow.getDljid());
        energyConsume1.setElectric(Double.valueOf(df.format((random.nextInt(201)+100)*f*4)));
        energyConsume1.setWater(Double.valueOf(df.format((random.nextInt(10)+1)*f*4)));
        energyConsume1.setOil(Double.valueOf(df.format((random.nextInt(31)+10)*f*4)));
        energyConsume1.setReportid(producereport.getReportid());
        //皮带机
        energyConsume2.setDevid(baseFlow.getDljid());
        energyConsume2.setElectric(Double.valueOf(df.format((random.nextInt(201)+100)*f*4)));
        energyConsume2.setWater(Double.valueOf(df.format((random.nextInt(10)+1)*f*4)));
        energyConsume2.setOil(Double.valueOf(df.format((random.nextInt(31)+10)*f*4)));
        energyConsume2.setReportid(producereport.getReportid());
        return true;
    }

    @Override
    public List<EnergyConsume> waterConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.waterConsume(map);
    }
    @Override
    public List<EnergyConsume> flowConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.flowConsume(map);
    }

    @Override
    public List<EnergyConsume> devTypeConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.devTypeConsume(map);
    }

    @Override
    public List<EnergyConsume> devConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.devConsume(map);
    }

    @Override
    public List<EnergyConsume> electricConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.electricConsume(map);
    }

    @Override
    public List<EnergyConsume> oilConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.oilConsume(map);
    }

    @Override
    public Map<String, Object> listChangeMapLeftJoin(List<EnergyConsume> energyConsumes) {

        // 创建columns数组并存入数据(即流程名称及"月份")
        //  长度为jobAmounts.size()+1
        String[] columns = new String[energyConsumes.size()+1];
        columns[0]="月份";
        for (int i = 1; i < columns.length; i++) {
            columns[i]=energyConsumes.get(i-1).getNames();
        }
      // 创建rows<Map>集合,即对象为Map的rows集合
        List<Map> rows = new ArrayList<>();
        // 创建一个数组存入月份,即一月~十二月,便于生成rowsObject中的"月份"属性
        String[] months ={"一月","二月","三月","四月","五月","六月",
                "七月","八月","九月","十月","十一月","十二月"};
            // 设置月份属性并将月份属性存入rows集合
             //遍历months数组
        for (String month:months){
            // 创建map对象存入属性"月份"
            Map<String,String> map = new HashMap<>();
         // 生成属性
            map.put("月份",month);
        // 存入rows集合
            rows.add(map);
        }
        // 遍历energyConsumes集合,向rows存入解析数据
        for(EnergyConsume ja:energyConsumes){
    // 判断columns长度,即除"月份"对象是否有其他数据
            if(columns.length!=1) {
                //            判断数据是否为空
                if (ja.getAmounts() == null) {
                    for (int i = 0; i < rows.size(); i++) {
//                向对应的rowsObject存入属性及值
                        rows.get(i).put(ja.getNames(),"0");
                    }
                } else {
//              分割数据
                    String[] arr = ja.getAmounts().split("[,]");
//              通过fori遍历rows,便于向rows表中对应的对象存入分割的数据
                    for (int i = 0; i < rows.size(); i++) {
//                向对应的rowsObject存入属性及值
                        rows.get(i).put(ja.getNames(), arr[i]);
                    }
                }
            }
        }
//        创建chartData Map集合
        Map<String,Object> chartData = new HashMap<>();
//        将columns数组及rows集合存入chartData
        chartData.put("columns",columns);
        chartData.put("rows",rows);
        return chartData;//chartData


    }



}