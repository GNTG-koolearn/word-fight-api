package com.koolearn.wordfight.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.koolearn.wordfight.entity.User;
import com.koolearn.wordfight.service.GameService;
import com.koolearn.wordfight.service.UserService;
import com.koolearn.wordfight.util.system.Result;
import com.koolearn.wordfight.web.vo.GameDataVO;
import com.koolearn.wordfight.web.vo.PowerMapVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 */
@Api(tags = "游戏接口")
@RestController
@Slf4j
public class GameController {

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @ApiOperation(value = "获取随机对战数据", notes = "获取随机对战数据")
    @GetMapping("/v1/game/randomWordPkData")
    public Result getRandomWordPkData(@ApiParam("用户ID") @RequestParam String uid) {
        JSONObject jsonObject = gameService.randomWordPKData(uid);
        return new Result(jsonObject);
    }

    @ApiOperation(value = "保存对战数据接口", notes = "保存对战数据接口")
    @PostMapping("/v1/game/savePKRecord")
    public Result savePkRecord(@ApiParam("玩家游戏数据") @RequestBody GameDataVO gameDataVO) {
        return gameService.getResult(gameDataVO);
    }

    @ApiOperation(value = "获取玩家能力图谱", notes = "获取玩家能力图谱")
    @GetMapping("/v1/game/powerMap")
    public Result getPowerMap(@ApiParam("用户ID") @RequestParam String uid) {
        PowerMapVO powerMap = userService.getPowerMap(uid);
        return new Result(powerMap);
    }

    @ApiOperation(value = "获取世界玩家排行", notes = "获取世界玩家排行")
    @GetMapping("/v1/game/worldRank")
    public Result getWorldRank() {
        List<User> worldRank = userService.getWorldRank();
        return new Result(worldRank);
    }

    @ApiOperation(value = "申请游戏房间", notes = "申请游戏房间")
    @PostMapping("/v1/game/applyRoom")
    public Result inviteFriend(
            @ApiParam("用户ID") @RequestParam String uid
    ) {
        String roomId = gameService.applyRoom(uid);
        return new Result(roomId);
    }

    @ApiOperation(value = "接受朋友对战", notes = "接受朋友对战")
    @PostMapping("/v1/game/agree2Fight")
    public Result agree2Fight(
            @ApiParam("房间id") @RequestParam String roomId,
            @ApiParam("应战者uid") @RequestParam String accepterUid
    ) {
        gameService.agree2Fight(roomId, accepterUid);
        return new Result();
    }

    @ApiOperation(value = "获取房间信息", notes = "获取房间信息")
    @GetMapping("/v1/game/getRoomInfo")
    public Result getRoomInfo(
            @ApiParam("房间id") @RequestParam String roomId,
            @ApiParam("玩家uid") @RequestParam String uid
    ) {
        Map roomInfo = gameService.getRoomInfo(roomId, uid);
        return new Result(roomInfo);
    }

    @ApiOperation(value = "玩家使用一次道具", notes = "玩家使用一次道具")
    @PutMapping("/v1/game/useProp")
    public Result useProp(
            @ApiParam("玩家uid") @RequestParam String uid
    ){
        boolean result = userService.useProp(uid);
        return new Result(result);
    }

    @ApiOperation(value = "玩家添加一次道具", notes = "玩家添加一次道具")
    @PutMapping("/v1/game/addProp")
    public Result addProp(
            @ApiParam("玩家uid") @RequestParam String uid
    ){
        boolean result = userService.addProp(uid);
        return new Result(result);
    }

}
