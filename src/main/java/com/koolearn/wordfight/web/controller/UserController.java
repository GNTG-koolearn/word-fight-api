package com.koolearn.wordfight.web.controller;

import com.koolearn.wordfight.entity.User;
import com.koolearn.wordfight.service.UserService;
import com.koolearn.wordfight.service.WechatService;
import com.koolearn.wordfight.util.system.Result;
import com.koolearn.wordfight.util.system.statusenum.ErrorEnum;
import com.koolearn.wordfight.web.vo.LoginVO;
import com.koolearn.wordfight.web.vo.UserUpVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/6
 * Time: 11:36
 */
@Api(tags = "用户接口")
@RequestMapping("/v1")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private WechatService wechatService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="微信登录", notes = "微信登录")
    @PostMapping("/login-wechat")
    public Result loginWechat(@ApiParam("微信登录对象") @RequestBody LoginVO loginVo) {
        return wechatService.checkWechatInfo(loginVo);
    }

    @ApiOperation(value="获取用户信息", notes = "获取用户信息")
    @GetMapping("/user/info")
    public Result getUserInfo(@ApiParam("用户id") @RequestParam String uid) {
        User userById = userService.getUserByUid(uid);
        if (userById == null) {
            return new Result(ErrorEnum.NO_USER,null);
        }
        return new Result(userById);
    }

    @ApiOperation(value="更新用户信息", notes = "更新用户信息")
    @PutMapping("/user/info")
    public Result getUserInfo(@ApiParam("用户信息对象") @RequestBody UserUpVO userUpVO) {
        User save = userService.updateUserInfo(userUpVO);
        if (save != null) {
            return new Result(save);
        } else {
            return new Result(ErrorEnum.NO_USER,null);
        }
    }

}
