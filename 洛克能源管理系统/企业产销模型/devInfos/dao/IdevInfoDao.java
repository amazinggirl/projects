package com.qhit.devInfos.dao;

import com.qhit.devInfos.pojo.devInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23 0023.
 */
@Mapper
public interface IdevInfoDao {
    List<Map> usage(Map<String, Object> map);

    List<Map> intactRatio(Map<String, Object> map);

    List<Map> cost(Map<String, Object> map);

    List<Map> amount(Map<String, Object> map);

    List<Map> consume(Map<String, Object> map);
}
