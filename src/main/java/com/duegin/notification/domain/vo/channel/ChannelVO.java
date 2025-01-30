package com.duegin.notification.domain.vo.channel;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DueGin
 * @date 2025/1/27
 */
@Data
public class ChannelVO {

    private String createUsername;

    private Integer id;

    /**
     * 订阅通知名称
     */
    private String name;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 创建人
     */
    private Integer createUser;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
