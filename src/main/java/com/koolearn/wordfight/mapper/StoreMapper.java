package com.koolearn.wordfight.mapper;

import com.koolearn.wordfight.entity.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Store 表数据库控制层接口
 */
@Mapper
public interface StoreMapper extends SuperMapper<Store> {

    List<Store> getRandomStores();
}