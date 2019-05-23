package com.qhit.buseActive.service;

import java.util.List;

import com.qhit.artActive.pojo.ArtActive;
import com.qhit.buseActive.pojo.BuseActive;
/**
* Created by GeneratorCode on 2019/05/17
*/
public interface IBuseActiveService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BuseActive findById(Object id);

    List<BuseActive> search(BuseActive buseActive);

    boolean deleteByaid(int i);


    BuseActive dainzan(Integer acid, int i);

    BuseActive count(Integer acid);
}