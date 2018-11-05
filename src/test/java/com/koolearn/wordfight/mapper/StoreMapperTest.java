package com.koolearn.wordfight.mapper;

import com.koolearn.wordfight.base.BaseWebTest;
import com.koolearn.wordfight.entity.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 18:39
 */
@Slf4j
public class StoreMapperTest extends BaseWebTest {

    @Autowired
    StoreMapper storeMapper;

    @Test
    public void test() {
        Store store = new Store();

        store.setStoreId(1234L);
        store.setWord("abcd");
        store.setWordId(1234);
        store.setQuestionTitle("questionTitle");
        store.setQuestionType(1);
        store.setOptionA("a");
        store.setTrueOption("A");
        int insert = storeMapper.insert(store);
        System.out.println("insert = " + insert);
        insert = storeMapper.insert(store);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testGetRandomResult() {
        List<Store> randomStores = storeMapper.getRandomStores();

        log.info("randomStores:{}", randomStores);
        log.info("randomStores.size:{}", randomStores.size());
    }

}