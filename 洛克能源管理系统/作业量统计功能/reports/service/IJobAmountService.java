package com.qhit.reports.service;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.reports.pojo.JobAmount;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
public interface IJobAmountService {

    List<JobAmount> flowAmount(String year, BaseUser baseUser);

    List<JobAmount> devtypeAmount(String year, BaseUser baseUser);

    List<JobAmount> devAmount(String year, BaseUser baseUser);

    Map<String,Object> listChangeMapLeftJoin(List<JobAmount> jobAmounts);

}
