package com.duegin.notification.domain.vo.channel.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DueGin
 * @date 2025/1/28
 */
@Data
public class ChannelUserVO {

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 通道ID
     */
    private Integer channelId;

    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 加入时间
     */
    private LocalDateTime createTime;

}
