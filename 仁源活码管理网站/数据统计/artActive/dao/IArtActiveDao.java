package com.qhit.artActive.dao;

import com.qhit.artActive.pojo.ArtActive;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
* Created by GeneratorCode on 2019/05/10
*/

@Mapper  
public interface IArtActiveDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<ArtActive> search(ArtActive artActive);

    List findByTitle(Object title);

    List findByFid(Object fid);

    List findByAid(Object aid);

    List findByCreattime(Object creattime);

    List findByStart(Object start);

    List findByContent(Object content);

}