package com.koolearn.wordfight.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koolearn.wordfight.entity.User;
import com.koolearn.wordfight.exception.BizException;
import com.koolearn.wordfight.mapper.UserMapper;
import com.koolearn.wordfight.util.system.statusenum.ErrorEnum;
import com.koolearn.wordfight.web.vo.PowerMapVO;
import com.koolearn.wordfight.web.vo.UserUpVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 15:41
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper,User> {

    public static final int PROP_MAX_COUNT = 5;

    @Autowired
    UserMapper userMapper;

    /**
     * 玩家使用道具
     * @param uid
     * @return
     */
    public boolean useProp(String uid) {
        User userByUid = getUserByUid(uid);

        if (userByUid == null) {
            throw new BizException(ErrorEnum.NO_USER);
        }

        if (userByUid.getPropCount() == 0) {
            throw new BizException(ErrorEnum.NO_ENOUGH_PROPS);
        }

        userByUid.setPropCount(userByUid.getPropCount() - 1);

        int upCount = userMapper.updateById(userByUid);

        return upCount > 0;
    }

    /**
     * 玩家添加道具+1
     * @param uid
     * @return
     */
    public boolean addProp(String uid) {
        User userByUid = getUserByUid(uid);

        if (userByUid == null) {
            throw new BizException(ErrorEnum.NO_USER);
        }

        if (userByUid.getPropCount() == PROP_MAX_COUNT) {
            throw new BizException(ErrorEnum.TOO_MUCH_PROPS);
        }

        userByUid.setPropCount(userByUid.getPropCount() + 1);

        int upCount = userMapper.updateById(userByUid);

        return upCount > 0;
    }

    public User getUserByUnionId(String unionId) {
        if (StringUtils.isEmpty(unionId)) {
            return null;
        }
        User user = new User();
        user.setUnionId(unionId);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);

        return userMapper.selectOne(userQueryWrapper);
    }

    @Transactional
    public User addWechatUser(User user2Add) {
        if (user2Add == null) {
            return null;
        }
        User userByUnionId = getUserByUnionId(user2Add.getUnionId());
        if (userByUnionId == null) {
            userMapper.insert(user2Add);
        }
        return userByUnionId;
    }

    public User getUserByUid(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        User user = new User();
        user.setUid(uid);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);

        return userMapper.selectOne(userQueryWrapper);
    }

    public User updateUserInfo(UserUpVO userUpVO) {
        if (userUpVO == null) {
            return null;
        }
        User userByUid = getUserByUid(userUpVO.getUid());
        userByUid.setUserName(userUpVO.getName());
        int count = userMapper.updateById(userByUid);
        return count > 0 ? userByUid : null;
    }

    /**
     * 获取玩家能力图谱
     * @param uid
     * @return
     */
    public PowerMapVO getPowerMap(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }

        User userByUid = getUserByUid(uid);

        PowerMapVO powerMapVO = new PowerMapVO();
        powerMapVO.setAttack(userByUid.getAttack());
        powerMapVO.setDefense(userByUid.getDefense());
        powerMapVO.setProps(userByUid.getProps());
        powerMapVO.setReputation(userByUid.getReputation());
        if (userByUid.getTotalRounds() == 0) {
            powerMapVO.setWinRate(0);
        } else {
            powerMapVO.setWinRate((userByUid.getWinRounds()/userByUid.getTotalRounds())*100);
        }

        return powerMapVO;
    }

    /**
     * 获取世界排名前10的
     * @return
     */
    public List<User> getWorldRank() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.ne("deleted", 1).orderByDesc("score").last("limit 10");
        return userMapper.selectList(userQueryWrapper);
    }
}
