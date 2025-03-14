package com.duegin.notification.domain.dto.channel.user.subscribe;

import lombok.Data;

/**
 * @author DueGin
 * @date 2025/1/30
 */
@Data
public class ChannelUserSubscribePageDTO {
    /**
     * 频道UUID
     */
    protected String uuid;
    /**
     * 用户ID
     */
    protected Integer userId;
    /**
     * 频道名称
     */
    protected String channelName;

}
