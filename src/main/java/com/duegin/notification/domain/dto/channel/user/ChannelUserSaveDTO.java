package com.duegin.notification.domain.dto.channel.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author DueGin
 * @date 2025/1/28
 */
@Data
public class ChannelUserSaveDTO {
    @NotNull(message = "频道不能为空")
    private Integer channelId;
    @NotNull(message = "用户不能为空")
    private Integer userId;
}
