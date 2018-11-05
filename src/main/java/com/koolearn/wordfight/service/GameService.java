package com.koolearn.wordfight.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.koolearn.wordfight.entity.User;
import com.koolearn.wordfight.exception.BizException;
import com.koolearn.wordfight.util.RobotUtils;
import com.koolearn.wordfight.util.Utils;
import com.koolearn.wordfight.util.system.Result;
import com.koolearn.wordfight.util.system.statusenum.ErrorEnum;
import com.koolearn.wordfight.web.vo.GameDataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 17:54
 */
@Service
@Slf4j
public class GameService {

    //redis的key过期时间
    public static final Integer KEY_EXPIRE_TIME = 50;

    @Autowired
    StoreService storeService;

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    public void agree2Fight(String roomId, String accepterUid) {

        User accepter = userService.getUserByUid(accepterUid);
        if (accepter == null) {
            throw new BizException(ErrorEnum.NO_ACCEPTER);
        }

        String inviterUid = (String)redisTemplate.opsForValue().get(roomId);

        User inviter = userService.getUserByUid(inviterUid);
        if (inviter == null) {
            throw new BizException(ErrorEnum.NO_INVITER);
        }

        String roomKey = String.format("room:%s",roomId);
        JSONArray randomQuizArray = storeService.getRandomQuizArray();

        redisTemplate.opsForHash().put(roomKey,"inviter",inviterUid);
        redisTemplate.opsForHash().put(roomKey,"inviterAvatar",inviter.getAvatar());
        redisTemplate.opsForHash().put(roomKey,"inviterNickname",inviter.getUserName());
        redisTemplate.opsForHash().put(roomKey,"accepter",accepterUid);
        redisTemplate.opsForHash().put(roomKey,"accepterAvatar",accepter.getAvatar());
        redisTemplate.opsForHash().put(roomKey,"accepterNickname",accepter.getUserName());
        redisTemplate.opsForHash().put(roomKey,"stores",randomQuizArray);
        redisTemplate.opsForHash().put(roomKey,"inviterProp",inviter.getPropCount());
        redisTemplate.opsForHash().put(roomKey, "accepterProp", accepter.getPropCount());
        redisTemplate.opsForHash().put(roomKey,"finish",2);
        redisTemplate.expire(roomKey, KEY_EXPIRE_TIME, TimeUnit.MINUTES);
        //清除挑战者创建的房间redis记录
        redisTemplate.delete(roomId);
    }

    public String applyRoom(String uid) {
        User userByUid = userService.getUserByUid(uid);
        if (userByUid == null) {
            throw new BizException(ErrorEnum.NO_USER);
        }

        String roomId = Utils.uuid();

        redisTemplate.opsForValue().set(roomId,userByUid.getUid(),KEY_EXPIRE_TIME, TimeUnit.MINUTES);

        return roomId;

    }

    public String getRandomImg() {
        List<String> imgList = Lists.newArrayList(
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328319166.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328322068.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328323519.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328325781.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328326670.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328327700.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328329540.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328330398.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328331771.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328333144.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328333940.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328339088.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328341194.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328343955.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328344938.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328498316.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328499205.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328500204.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328501951.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328502856.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328503760.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328505352.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328506397.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328507832.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328509501.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328510344.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328511404.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328513682.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328514509.PNG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328515351.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328517114.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328517925.JPG",
                "http://images.koolearn.com/fe_upload/2018/10/2018-10-12-1539328518814.JPG",
                "http://images.koolearn.com/fe_upload/20182018-10-12-1539328519813.JPG");

        int randomIndex = new Random().nextInt(imgList.size());
        return imgList.get(randomIndex);
    }


    public JSONObject randomWordPKData(String uid) {

        JSONObject resObj = new JSONObject();

        JSONArray randomWordStore= storeService.getRandomQuizArray();
        resObj.put("randomWordStore", randomWordStore);

        JSONObject randomVirtualUser = new JSONObject();
        randomVirtualUser.put("id", 001);
        randomVirtualUser.put("virtualName", RobotUtils.getChineseName());
        randomVirtualUser.put("imgUrl", getRandomImg());
        resObj.put("randomVirtualUser", randomVirtualUser);

        if (randomWordStore == null || randomWordStore.size() <= 0) {
            return null;
        }

        List<String> trueOptionList = new ArrayList<>(10);

        for (Object itm : randomWordStore) {
            JSONObject itmObj = (JSONObject) itm;
            String trueOption = (String)itmObj.get("trueOption");
            trueOptionList.add(trueOption);
        }


        List<String> robotAnswer = getRobotAnswer(trueOptionList);

        resObj.put("robotAnswer", robotAnswer);

        User userByUid = userService.getUserByUid(uid);

        //玩家道具剩余数
        Integer propCount = userByUid.getPropCount();

        resObj.put("propCount", propCount);

        log.info("resObj:{}", resObj);

        return resObj;

    }

    /**
     * 根据正确答案获取机器人答案
     * @param trueOptionList
     * @return
     */
    public List<String> getRobotAnswer(List<String> trueOptionList) {

        if (trueOptionList == null || trueOptionList.isEmpty()) {
            return null;
        }

        int[] trueOptionArray = RobotUtils.convertOptionChar2Int(trueOptionList);

        int[] robotAnswer = RobotUtils.deepCopyArray(trueOptionArray);


        //机器人错误率：0%，20%，50%
        List<Double> robotType = new ArrayList<>();
        robotType.add(0.0);
        robotType.add(0.2);
        robotType.add(0.5);

        int robotRandomType = RandomUtils.nextInt(0, 3);

        int errorCount = (int) (robotAnswer.length * robotType.get(robotRandomType));

        int[] errorIndexArray = RobotUtils.randomArray(0, robotAnswer.length-1, errorCount);

        log.info("robotRandomType:{},errorCount:{},errorIndexArray:{}",robotRandomType,errorCount,Arrays.toString(errorIndexArray));

        //根据错误率，替换掉该位置的正确答案
        for (int i = 0; i < errorIndexArray.length; i++) {
            int trueAns = robotAnswer[errorIndexArray[i]];
            Set<Integer> options = RobotUtils.getOptions();
            options.remove(trueAns);
            List<Integer> list = new ArrayList(options);

            robotAnswer[errorIndexArray[i]] = list.get(RandomUtils.nextInt(0, 3));
        }

        return RobotUtils.convertOptionInt2Char(robotAnswer);

    }

    public Map getRoomInfo(String roomId, String uid) {
        String roomKey = String.format("room:%s",roomId);
        return redisTemplate.opsForHash().entries(roomKey);
    }

    public Result getResult(GameDataVO gameDataVO) {
        User userByUid = userService.getUserByUid(gameDataVO.getUid());

        userByUid.setScore(userByUid.getScore() + gameDataVO.getScore());
        userByUid.setTotalRounds(userByUid.getTotalRounds() + 1);
        if (1 == gameDataVO.getIsWin()) {
            userByUid.setWinRounds(userByUid.getWinRounds() + 1);
        }

        //统计道具实用情况
        userByUid.setProps(userByUid.getProps() + gameDataVO.getPropCount());

        //好友对战
        if (2 == gameDataVO.getPkType()) {
            if (StringUtils.isEmpty(gameDataVO.getRoomId())) {
                throw new BizException(ErrorEnum.NO_ROOMID_ERRROR);
            }

            String inviterId = (String) redisTemplate.opsForHash().get(gameDataVO.getRoomId(), "inviter");
            String accepterId = (String) redisTemplate.opsForHash().get(gameDataVO.getRoomId(), "accepter");
            //玩家是挑战者并且获胜
            if (gameDataVO.getUid().equals(inviterId) && gameDataVO.getIsWin() == 1) {
                userByUid.setAttack(userByUid.getAttack() + 1);
            }
            //玩家是应战者并且获胜
            if (gameDataVO.getUid().equals(accepterId) && gameDataVO.getIsWin() == 1) {
                userByUid.setDefense(userByUid.getDefense() + 1);
            }
            //调整者的声望+1
            User inviter = userService.getUserByUid(inviterId);

        }

        boolean result = userService.saveOrUpdate(userByUid);
        if (result) {
            //清除redis中该局游戏的记录
            Long finish = redisTemplate.opsForHash().increment(gameDataVO.getRoomId(), "finish", -1);
            log.info("finish:{}", finish);
            if (finish == 0) {
                Boolean delete = redisTemplate.delete(gameDataVO.getRoomId());
                log.info("roomId:{} is deleted:{}", gameDataVO.getRoomId(), delete);
            }
            return new Result();
        } else {
            return new Result(ErrorEnum.USER_SAVE_ERROR);
        }
    }
}
