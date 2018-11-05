package com.koolearn.wordfight.service;

import com.koolearn.wordfight.base.BaseWebTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 18:08
 */
@Slf4j
public class SpiderServiceTest extends BaseWebTest {

    @Autowired
    SpiderService spiderService;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            spiderService.getRandomDataFromUrl();
        }
        Thread.currentThread().join();
    }

}
