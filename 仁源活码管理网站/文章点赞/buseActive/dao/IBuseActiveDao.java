package com.qhit.buseActive.dao;

import com.qhit.artActive.pojo.ArtActive;
import org.apache.ibatis.annotations.Mapper;
import com.qhit.buseActive.pojo.BuseActive;
import java.util.List;

/** 
* Created by GeneratorCode on 2019/05/17
*/

@Mapper  
public interface IBuseActiveDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<BuseActive> search(BuseActive buseActive);

    List findByAid(Object aid);

    List findByAcid(Object acid);

    List<BuseActive> findByaId(int i);

    boolean deleteByaid(BuseActive buseActive);

    List<BuseActive> dainzan(Integer acid, int i);

    List<BuseActive> count(Integer acid);
}