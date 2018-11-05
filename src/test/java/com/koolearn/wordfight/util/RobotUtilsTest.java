package com.koolearn.wordfight.util;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 20:54
 */
public class RobotUtilsTest {

    @Test
    public void test() {
        List<String> opList = Lists.newArrayList("A","B","A","A","A","D","A","A","C","A");

        opList = RobotUtils.convertOptionInt2Char(RobotUtils.convertOptionChar2Int(opList));

        System.out.println(opList);

    }

    @Test
    public void test3() {
        int[] ints = RobotUtils.randomArray(0, 9, 3);

        System.out.println("ints = " + Arrays.toString(ints));
        ints = RobotUtils.randomArray(0, 9, 3);
        System.out.println("ints = " + Arrays.toString(ints));
        ints = RobotUtils.randomArray(0, 9, 3);
        System.out.println("ints = " + Arrays.toString(ints));

    }

    @Test
    public void test2() {
        int[] trueOptionArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] ints = RobotUtils.deepCopyArray(trueOptionArray);

        ints[3] = 5;
        ints[5] = 10;

        System.out.println(Arrays.toString(trueOptionArray));
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void getChineseName() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RobotUtils.getChineseName());

        }

    }
}