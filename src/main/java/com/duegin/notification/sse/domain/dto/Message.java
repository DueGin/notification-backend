package com.duegin.notification.sse.domain.dto;

import lombok.Data;

/**
 * @author DueGin
 * @date 2025/1/13
 */
@Data
public class Message {

    private String uuid;
    private String message;
    private String channelId;
    private Integer total;
}
