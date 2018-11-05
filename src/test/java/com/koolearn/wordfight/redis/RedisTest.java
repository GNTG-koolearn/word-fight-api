package com.koolearn.wordfight.redis;

import com.koolearn.wordfight.base.BaseWebTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/28
 * Time: 12:21
 */
@Slf4j
public class RedisTest extends BaseWebTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("zs","zs",5, TimeUnit.MINUTES);
    }


}
