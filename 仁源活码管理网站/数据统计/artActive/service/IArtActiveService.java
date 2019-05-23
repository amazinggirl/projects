package com.qhit.artActive.service;

import com.qhit.artActive.pojo.ArtActive;

import javax.swing.text.AbstractDocument;
import java.util.List;
/**
* Created by GeneratorCode on 2019/05/10
*/
public interface IArtActiveService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    ArtActive findById(Object id);

    List<ArtActive> search(ArtActive artActive);


}