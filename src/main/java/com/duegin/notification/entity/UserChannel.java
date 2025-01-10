package com.duegin.notification.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * 用户订阅通知 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Data
@Table(value = "user_channel")
public class UserChannel {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(value = "user_id")
    private Integer userId;

    /**
     * 通道ID
     */
    @Column(value = "channel_id")
    private Integer channelId;


}
