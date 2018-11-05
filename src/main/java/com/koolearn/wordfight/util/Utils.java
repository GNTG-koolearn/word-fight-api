package com.koolearn.wordfight.util;

import java.io.*;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 11:09
 */
public class Utils {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
