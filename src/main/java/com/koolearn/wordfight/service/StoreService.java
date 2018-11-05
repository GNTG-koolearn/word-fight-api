package com.koolearn.wordfight.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koolearn.wordfight.entity.Store;
import com.koolearn.wordfight.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 20:37
 */
@Service
public class StoreService extends ServiceImpl<StoreMapper, Store> {

    @Autowired
    StoreMapper storeMapper;

    public List<Store> getRandomQuiz() {
        return storeMapper.getRandomStores();
    }

    public JSONArray getRandomQuizArray() {
        List<Store> randomStores = storeMapper.getRandomStores();
        return JSONArray.parseArray(JSON.toJSONString(randomStores));
    }
}
