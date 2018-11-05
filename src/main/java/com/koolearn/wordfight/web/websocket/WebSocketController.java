package com.koolearn.wordfight.web.websocket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koolearn.wordfight.service.StoreService;
import com.koolearn.wordfight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    StoreService storeService;

    @Autowired
    UserService userService;

    @MessageMapping("/v1/game/receive/{roomId}")
    @SendToUser("/v1/game/send")
    public void handleMsg(@DestinationVariable String roomId, StompHeaderAccessor headerAccessor, String msg) throws Exception {

        log.info("roomId:{},headerAccessor:{},msg:{}",roomId,headerAccessor,msg);

        String inviterId = (String)redisTemplate.opsForHash().get(roomId, "inviter");
        String accepterId = (String)redisTemplate.opsForHash().get(roomId, "accepter");

        log.info("inviterId:{},accepterId:{}", inviterId, accepterId);

        JSONObject msgObj = JSONObject.parseObject(msg);

        log.info("msgObj:{}", msgObj);


        String commond = (String) msgObj.get("commond");
        String from = (String)msgObj.get("from");
        if (StringUtils.isNotEmpty(commond)) {
            switch (commond) {
                case "quiz":
                    JSONArray stores = (JSONArray)redisTemplate.opsForHash().get(roomId, "stores");
                    log.info("stores:{}",stores);
                    msgObj.put("stores", stores);
                    msgObj.put("from", "server");
                    break;
                case "answer":
                    Integer storeId = (Integer)msgObj.get("storeId");
                    String answer = (String)msgObj.get("answer");
                    msgObj.put("from", from);
                    break;
                case "runaway":
                    break;
                case "useprop":
                    break;
                default:
                    break;
            }
        }


        log.info("server response msg:{}", msgObj);
        messagingTemplate.convertAndSendToUser(roomId, "/v1/game/send", msgObj.toJSONString());
    }
}