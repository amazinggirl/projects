package com.qhit.reports.service.impl;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.reports.dao.IJobAmountDao;
import com.qhit.reports.pojo.JobAmount;
import com.qhit.reports.service.IJobAmountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
@Service
public class JobAmountServiceImpl implements IJobAmountService {
    @Resource
    IJobAmountDao dao;

    @Override
    public List<JobAmount> flowAmount(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.flowAmount(map);
    }

    @Override
    public List<JobAmount> devtypeAmount(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.devtypeAmount(map);
    }

    @Override
    public List<JobAmount> devAmount(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",baseUser.getCompid());
        return dao.devAmount(map);
    }



    @Override
    public Map<String, Object> listChangeMapLeftJoin(List<JobAmount> jobAmounts) {

        //        创建columns数组并存入数据(即流程名称及"月份")
//        长度为jobAmounts.size()+1
        String[] columns = new String[jobAmounts.size()+1];
        columns[0]="月份";
        for (int i = 1; i < columns.length; i++) {
            columns[i]=jobAmounts.get(i-1).getNames();
        }
//        创建rows<Map>集合,即对象为Map的rows集合
        List<Map> rows = new ArrayList<>();
//        创建一个数组存入月份,即一月~十二月,便于生成rowsObject中的"月份"属性
        String[] months ={"一月","二月","三月","四月","五月","六月",
                "七月","八月","九月","十月","十一月","十二月"};
//        设置月份属性并将月份属性存入rows集合
//        遍历months数组
        for (String month:months){
//            创建map对象存入属性"月份"
            Map<String,String> map = new HashMap<>();
//            生成属性
            map.put("月份",month);
//            存入rows集合
            rows.add(map);
        }
//        遍历jobAmounts集合,向rows存入解析数据
        for(JobAmount ja:jobAmounts){
//            判断columns长度,即除"月份"对象是否有其他数据
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
