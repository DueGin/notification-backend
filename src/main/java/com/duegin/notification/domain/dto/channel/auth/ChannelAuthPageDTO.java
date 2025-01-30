package com.duegin.notification.domain.dto.channel.auth;

import lombok.Data;

/**
 * @author DueGin
 * @date 2025/1/30
 */
@Data
public class ChannelAuthPageDTO {
    private String uuid;
    private Integer channelId;
    private Integer userId;
}
