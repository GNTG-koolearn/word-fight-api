package com.koolearn.wordfight.service;

import com.koolearn.wordfight.base.BaseWebTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/26
 * Time: 13:16
 */
@Slf4j
public class WebSocketServiceTest extends BaseWebTest {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Test
    public void say() {
        messagingTemplate.convertAndSend("/topic/getResponse","hello,client");

    }
}