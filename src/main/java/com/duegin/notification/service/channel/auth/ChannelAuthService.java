package com.duegin.notification.service.channel.auth;

import com.duegin.notification.mapper.ChannelAuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DueGin
 * @date 2025/1/10
 */
@Slf4j
@Service
public class ChannelAuthService {

    @Resource
    private ChannelAuthMapper channelAuthMapper;

    public void checkAccount(String publicKey){
        log.info("checkAccount ==> publicKey: {}", publicKey);

    }
}
