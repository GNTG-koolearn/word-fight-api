package com.koolearn.wordfight.service;

import com.koolearn.wordfight.base.BaseWebTest;
import com.koolearn.wordfight.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/28
 * Time: 09:23
 */
@Slf4j
public class UserServiceTest extends BaseWebTest {

    private String uid;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        uid = "zs";
    }

    @Test
    @Transactional
    public void test() {

        List<User> userList = new ArrayList<>();

        User user = new User();

        user.setUserName("zs");
        user.setUid("111111");
        user.setUnionId("111111");
        user.setAvatar("adafsdfsdadfa");
        user.setStatus(1);
        user.setDeleted(0);

        userList.add(user);

        user = new User();

        user.setUserName("lisi");
        user.setUid("123456");
        user.setUnionId("123456");
        user.setAvatar("adafsdfsdadfa");
        user.setStatus(1);
        user.setDeleted(0);

        userList.add(user);

        userService.saveBatch(userList);
    }

    @Test
    @Transactional
    public void testUseProp() {

        User userByUid = userService.getUserByUid(uid);

        int beforeProp = userByUid.getPropCount();

        log.info("beforeProp:{}", beforeProp);

        boolean result = userService.useProp(uid);

        assertTrue("使用道具", result);


        userByUid = userService.getUserByUid(uid);

        int afterProp = userByUid.getPropCount();

        log.info("afterProp:{}", afterProp);

        assertEquals("数量相等", afterProp, beforeProp - 1);


    }

    @Test
    @Transactional
    public void testAddProp() {

        User userByUid = userService.getUserByUid(uid);

        int beforeProp = userByUid.getPropCount();

        log.info("beforeProp:{}", beforeProp);

        boolean result = userService.addProp(uid);

        assertTrue("使用道具", result);


        userByUid = userService.getUserByUid(uid);

        int afterProp = userByUid.getPropCount();

        log.info("afterProp:{}", afterProp);

        assertEquals("数量相等", afterProp, beforeProp + 1);


    }

}
