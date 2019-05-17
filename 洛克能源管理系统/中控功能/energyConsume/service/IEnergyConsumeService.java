package com.qhit.energyConsume.service;

import java.util.List;
import java.util.Map;

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.produceReport.pojo.ProduceReport;

/**
* Created by GeneratorCode on 2019/04/11
*/
public interface IEnergyConsumeService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    EnergyConsume findById(Object id);

    List<EnergyConsume> search(EnergyConsume energyConsume);
    boolean inserts(ProduceReport producereport, BaseFlow baseFlow);

    List<EnergyConsume> waterConsume(String year, BaseUser baseUser);

    Map<String,Object> listChangeMapLeftJoin(List<EnergyConsume> energyConsumes);

    List<EnergyConsume> flowConsume(String year, BaseUser baseUser);

    List<EnergyConsume> devTypeConsume(String year, BaseUser baseUser);

    List<EnergyConsume> devConsume(String year, BaseUser baseUser);

    List<EnergyConsume> electricConsume(String year, BaseUser baseUser);

    List<EnergyConsume> oilConsume(String year, BaseUser baseUser);
}