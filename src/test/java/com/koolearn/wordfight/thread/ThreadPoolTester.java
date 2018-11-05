package com.koolearn.wordfight.thread;

import com.koolearn.wordfight.base.BaseWebTest;
import com.koolearn.wordfight.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 20:09
 */
@Slf4j
public class ThreadPoolTester extends BaseWebTest {

    @Autowired
    Executor executor;

    @Autowired
    SpiderService spiderService;

    @Test
    public void testSpider() {
        ExecutorService executorService = (ExecutorService) executor;

        executorService.submit(()->{
            spiderService.getRandomDataFromUrl();
        });

        executorService.shutdown();
    }
}
