package com.qhit.reports.controller;

import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseCompany.service.IBaseCompanyService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.produceReport.service.IProduceReportService;
import com.qhit.reports.pojo.JobAmount;
import com.qhit.reports.service.IJobAmountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
@RestController
@RequestMapping("/jobAmount")
public class JobAmountController {

    @Resource
    IJobAmountService jobAmountService;


    @RequestMapping("/flowAmount")
    public Map<String, Object> flowAmount(String year, HttpSession session){
     /*   BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        baseUser.setCompid(baseUser.getCompid());*/
        List<JobAmount> jobAmounts = jobAmountService.flowAmount(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = jobAmountService.listChangeMapLeftJoin(jobAmounts);
        return chartData;
    }
    @RequestMapping("/devTypeAmount")
    public Map<String, Object> devtypeAmount(String year, HttpSession session){
        List<JobAmount> jobAmounts = jobAmountService.devtypeAmount(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = jobAmountService.listChangeMapLeftJoin(jobAmounts);
        return chartData;
    }
    @RequestMapping("/devAmount")
    public Map<String, Object> devAmount(String year, HttpSession session){
        List<JobAmount> jobAmounts = jobAmountService.devAmount(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = jobAmountService.listChangeMapLeftJoin(jobAmounts);
        return chartData;
    }


}
