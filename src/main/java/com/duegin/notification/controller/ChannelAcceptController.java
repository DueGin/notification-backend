package com.duegin.notification.controller;

import com.duegin.notification.domain.dto.ChannelAcceptDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/channel/accept")
public class ChannelAcceptController {

    @PostMapping("/")
    public void accept(@RequestBody ChannelAcceptDTO acceptDTO){

    }
}
