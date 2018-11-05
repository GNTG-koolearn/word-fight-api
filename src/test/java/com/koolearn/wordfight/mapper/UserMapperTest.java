package com.koolearn.wordfight.mapper;

import com.koolearn.wordfight.base.BaseWebTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 10:52
 */
@Slf4j
public class UserMapperTest extends BaseWebTest {

    @Autowired
    UserMapper userMapper;


    @Test
    public void test() {

        /*User user = new User();

        user.setUserName("lisi");
        user.setUid(Utils.uuid());
        user.setUnionId(Utils.uuid());
        user.setAvatar("adafsdfsdadfa");
        user.setStatus(1);
        user.setDeleted(0);

        Integer insert = userMapper.insert(user);

        log.info("insert:{}",insert);*/

        /*int deleted = userMapper.deleteById(3L);

        log.info("deleted:{}", deleted);*/

        /*User user = new User();
        user.setId(3L);
        user.setUserName("李四");
        user.setStatus(0);
        user.setAvatar("aaaaa");

        int updateById = userMapper.updateById(user);

        log.info("updateById:{}",updateById);*/

        /*User user = new User();
        user.setId(3L);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(user);

        int delete = userMapper.delete(queryWrapper);

        log.info("delete:{}", delete);*/


        /*User user = userMapper.selectById(3);

        log.info("user:{}",user);*/
    }

}