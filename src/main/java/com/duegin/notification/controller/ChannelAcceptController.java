package com.duegin.notification.controller;

import com.duegin.notification.domain.dto.ChannelAcceptDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接收频道消息
 * @author DueGin
 * @date 2025/1/4
 */
@RestController
@RequestMapping("/api/channel/accept")
public class ChannelAcceptController {

    @PostMapping("/")
    public void accept(@RequestBody ChannelAcceptDTO acceptDTO){

    }
}
