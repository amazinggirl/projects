package com.qhit.devInfos.controller;

import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseCompany.service.IBaseCompanyService;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.devInfos.service.IdevInfoService;
import com.qhit.produceReport.service.IProduceReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23 0023.
 */
@RestController
@RequestMapping("/devInfo")
public class devInfoController {

    @Resource
    IdevInfoService infoService;


    /*1、港口间设备利用率对比*/
    @RequestMapping("/usage")
    public Map<String, Object> usage(String year , HttpSession session){
        return infoService.usage(year) ;

    }

    /**
     * 2、港口间设备完好率对比：
     * @param year
     * @return
     */
    @RequestMapping("/intactRatio")
    public Map<String,Object> intactRatio(String year){
        return infoService.intactRatio(year);
    }

    /**
     * 成本核算信息：
     * @param year
     * @param session
     * @return
     */
    @RequestMapping("/cost")
    public List<Map> cost(String year, HttpSession session){
        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        return infoService.cost(year,baseUser.getCompid());
    }

    /**
     * 港口间作业量对比
     * @param year
     * @return
     */
    @RequestMapping("/amount")
    public Map<String,Object> amount(String year){
        return infoService.amount(year);
    }

    /**
     * 港口间能耗对比
     * @param year
     * @return
     */
    @RequestMapping("/consume")
    public Map<String,Object> consume(String year){
        return infoService.consume(year);
    }
}
