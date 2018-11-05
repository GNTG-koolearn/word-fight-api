package com.koolearn.wordfight.other;

import com.alibaba.fastjson.JSONArray;
import com.koolearn.wordfight.base.BaseWebTest;
import com.koolearn.wordfight.util.RobotUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 18:25
 */
@Slf4j
public class Gists extends BaseWebTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test2() {
        String roomId = "room:bdd9283ae23f4d89a090171f986aee22";
        JSONArray stores = (JSONArray)redisTemplate.opsForHash().get(roomId, "stores");
        log.info("storesStr:{}", stores.toJSONString());
    }

    @Test
    public void test() {

        int[] trueOptionArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int[] robotAnswer = RobotUtils.deepCopyArray(trueOptionArray);


        //机器人错误率：0%，20%，50%
        List<Double> robotType = new ArrayList<>();
        robotType.add(0.0);
        robotType.add(0.2);
        robotType.add(0.5);

        int robotRandomType = RandomUtils.nextInt(0, 3);

        System.out.println("robotRandomType:"+robotRandomType);

        int errorCount = (int) (robotAnswer.length * robotType.get(robotRandomType));

        System.out.println("errorCount:"+errorCount);

        int[] errorIndexArray = RobotUtils.randomArray(0, robotAnswer.length-1, errorCount);

        System.out.println("errorIndexArray:"+ Arrays.toString(errorIndexArray));

        for (int i = 0; i < errorIndexArray.length; i++) {
            int trueAns = robotAnswer[errorIndexArray[i]];
            Set<Integer> options = RobotUtils.getOptions();
            options.remove(trueAns);
            List<Integer> list = new ArrayList(options);

            robotAnswer[errorIndexArray[i]] = list.get(RandomUtils.nextInt(0, 3));
        }

        System.out.println(Arrays.toString(robotAnswer));





        /*for (int i = 0; i <10 ; i++) {
            System.out.println(RandomUtils.nextInt(0, 2));
        }*/



    }
}
