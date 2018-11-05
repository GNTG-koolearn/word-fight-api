package com.koolearn.wordfight.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker//启用STOMP协议来传输基于代理（message broker）的消息
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个stomp的节点，使用SockJS协议
        registry.addEndpoint("/webSocketEndPoint/").setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setUserDestinationPrefix("/user");
        //使用内置的消息代理进行订阅和广播；路由消息的目标头以“/topic”或“/queue”开头。
        config.enableSimpleBroker("/topic", "/queue","/user");
        /*
         * 客户端发送过来的消息，需要以"/app"为前缀，再经过Broker转发给响应的Controller
         */
        config.setApplicationDestinationPrefixes("/app");
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //1. 判断是否首次连接请求
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    //2. 验证是否登录
                    String uid = accessor.getNativeHeader("uid").get(0);
                    String roomId = accessor.getNativeHeader("roomId").get(0);

                    String inviter = (String) redisTemplate.opsForHash().get(roomId, "inviter");
                    String accepter = (String)redisTemplate.opsForHash().get(roomId,"accepter");

                    log.info("start connecting...... uid:{},roomId:{},inviter:{},accepter:{}", uid, roomId, inviter, accepter);

                    if (accepter.equals(uid) || inviter.equals(uid)) {
                        return message;
                    } else {
                        return null;
                    }
                }
                //不是首次连接，已经成功登陆
                return message;
            }
        });
    }
}