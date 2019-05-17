package com.qhit.reports.dao;

import com.qhit.reports.pojo.JobAmount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
@Mapper
public interface IJobAmountDao {
    List<JobAmount> flowAmount(Map map);

    List<JobAmount> devtypeAmount(Map map);

    List<JobAmount> devAmount(Map map);


}
