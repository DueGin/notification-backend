package com.duegin.notification.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 频道授权 实体类。
 *
 * @author DueGin
 * @since 1.0
 */
@Accessors(chain = true)
@Data
@Table(value = "channel_auth")
public class ChannelAuth {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 订阅通知ID
     */
    @Column(value = "channel_id")
    private Integer channelId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 授权token
     */
    private String token;


    /**
     * 创建时间
     */
    @Column(value = "create_time")
    private Date createTime;

    @Column(value = "deleted")
    private Integer deleted;


}
