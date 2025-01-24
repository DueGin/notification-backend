package com.duegin.notification.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
* 频道消息
* @TableName channel_message
*/
@Accessors(chain = true)
@Data
public class ChannelMessage {

    @NotNull(message="[]不能为空")
    private Integer id;
    /**
    * 订阅通知ID
    */
    @NotNull(message="[订阅通知ID]不能为空")
    private Integer channelId;
    /**
    * 频道授权ID
    */
    @NotNull(message="[频道授权ID]不能为空")
    private Integer channelAuthId;
    /**
    * 发送方IP
    */
    @NotBlank(message="[发送方IP]不能为空")
    @Size(max= 128,message="编码长度不能超过128")
    private String ip;
    /**
    * 消息
    */
    @NotBlank(message="[消息]不能为空")
    private String message;
    /**
    * 接收时间
    */
    private LocalDateTime acceptTime;

}
