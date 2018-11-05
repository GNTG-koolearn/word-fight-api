package com.koolearn.wordfight.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.koolearn.wordfight.entity.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 20:15
 */
@Service
@Slf4j
public class SpiderService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StoreService storeService;

    @Async
    public void getRandomDataFromUrl() {

        int count = storeService.count(new QueryWrapper<>());

        log.info("count:{}", count);

        if (count > 500000) {
            return;
        } else {
            String url = "https://edu.10086.cn/edudctxcxapi/user/randomWordPkData?openId=null";
            ResponseEntity<LinkedHashMap> forEntity = restTemplate.getForEntity(url, LinkedHashMap.class);

            LinkedHashMap body = forEntity.getBody();

            JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(body));

            JSONObject data = (JSONObject)itemJSONObj.get("data");

            JSONArray randomWordStore = (JSONArray)data.get("randomWordStore");

            List<Store> storeList = new ArrayList<>();
            for (int i = 0; i < randomWordStore.size(); i++) {
                JSONObject itmObj = (JSONObject)randomWordStore.get(i);
                Store store = JSON.parseObject(itmObj.toJSONString(), Store.class);
                storeList.add(store);
            }

            try {
                storeService.saveBatch(storeList);
            } catch (Exception e) {
            }

            getRandomDataFromUrl();
        }
    }
}
