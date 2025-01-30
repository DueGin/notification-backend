package com.duegin.notification.domain.vo.channel.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DueGin
 * @date 2025/1/13
 */
@Data
public class ChannelAuthVO {

    private Integer id;
    private Integer channelId;
    private String token;
    private LocalDateTime createTime;
}
