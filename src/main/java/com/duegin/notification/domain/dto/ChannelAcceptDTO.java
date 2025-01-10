package com.duegin.notification.domain.dto;

import lombok.Data;

/**
 * 接收消息DTO
 *
 * @author DueGin
 * @date 2025/1/4
 */
@Data
public class ChannelAcceptDTO {

    private String name;
    private String text;
    private String callbackUrl;
    private String publicKey;
}
