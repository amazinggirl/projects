package com.qhit.devInfos.service.impl;

import com.qhit.devInfos.dao.IdevInfoDao;
import com.qhit.devInfos.service.IdevInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23 0023.
 */
@Service
public class devInfoServiceImpl implements IdevInfoService {
    @Resource
    IdevInfoDao dao;
    @Override
    public Map<String, Object> usage(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.usage(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }

    @Override
    public Map<String, Object> intactRatio(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.intactRatio(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }

    @Override
    public List<Map> cost(String year, Integer compid) {
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",compid);
        List<Map> rows = dao.cost(map);
        return rows;
    }

    @Override
    public Map<String, Object> amount(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.amount(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }

    @Override
    public Map<String, Object> consume(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.consume(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }

}
