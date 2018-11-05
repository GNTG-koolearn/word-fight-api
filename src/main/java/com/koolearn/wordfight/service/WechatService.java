package com.koolearn.wordfight.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.koolearn.wordfight.entity.User;
import com.koolearn.wordfight.util.Utils;
import com.koolearn.wordfight.util.WechatDecriptUtils;
import com.koolearn.wordfight.util.system.Result;
import com.koolearn.wordfight.util.system.WechatPropertiy;
import com.koolearn.wordfight.util.system.statusenum.ErrorEnum;
import com.koolearn.wordfight.web.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/3
 * Time: 16:15
 */
@Service
@Slf4j
public class WechatService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WechatPropertiy wechatPropertiy;

    @Autowired
    UserService userService;

    public Result checkWechatInfo(LoginVO loginVo) {
        log.info("loginVO:{}"+loginVo);
        String appid = wechatPropertiy.getAppid();
        String secret = wechatPropertiy.getAppSecret();
        String wechatLoginUrl = wechatPropertiy.getLoginUrl();
        String path = String.format("%s?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", wechatLoginUrl, appid, secret, loginVo.getCode());
        log.info("wechat check Path:{}",path);

        ResponseEntity<String> entity = restTemplate.getForEntity(path, String.class);

        log.info("entity:{}", entity);

        JSONObject responseObj = JSON.parseObject(entity.getBody());

        log.info("responseObj:{}",responseObj);

        boolean hasErrorCode = responseObj.containsKey("errcode");

        if (!hasErrorCode) {
            final String openId = responseObj.getString("openid");
            final String sessionKey = responseObj.getString("session_key");

            //解析微信加密的用户敏感信息
            JSONObject decryptedData = WechatDecriptUtils.getDecryptedData(sessionKey, loginVo.getEncryptedData(), loginVo.getIv());

            String unionId = decryptedData.getString("unionId");
            String avatar = decryptedData.getString("avatarUrl");

            User user = new User();
            user.setUnionId(unionId);
            user.setUserName(loginVo.getNickName());
            user.setStatus(1);
            user.setAvatar(avatar);
            user.setDeleted(0);
            user.setUid(Utils.uuid());
            user = userService.addWechatUser(user);
            return new Result<>(user.getUid());
        } else {
            return new Result<>(ErrorEnum.WECHAT_LOGIN_ERROR.getCode(),ErrorEnum.WECHAT_LOGIN_ERROR.getName(),null);
        }
    }

}
