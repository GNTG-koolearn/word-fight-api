package com.koolearn.wordfight.mapper;

import com.koolearn.wordfight.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User 表数据库控制层接口
 */
@Mapper
public interface UserMapper extends SuperMapper<User> {

}