package com.koolearn.wordfight.service;

import com.koolearn.wordfight.web.dto.SocketResponseMessage;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 12:29
 */
@Service
public class WebSocketService {

    @SendTo("/topic/getResponse")
    public SocketResponseMessage say(String message) {
        return new SocketResponseMessage(String.format("发送消息:%s", message));
    }
}
