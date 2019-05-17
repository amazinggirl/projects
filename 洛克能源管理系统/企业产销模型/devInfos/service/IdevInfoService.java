package com.qhit.devInfos.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23 0023.
 */
public interface IdevInfoService {


    Map<String,Object> usage(String year);

    Map<String,Object> intactRatio(String year);

    List<Map> cost(String year, Integer compid);

    Map<String,Object> amount(String year);

    Map<String,Object> consume(String year);
}
