package com.duegin.notification.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通知通道配置 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Data
@Table(value = "channel")
public class Channel {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 订阅通知名称
     */
    @Column(value = "name")
    private String name;

    /**
     * 创建人
     */
    @Column(value = "create_user")
    private Integer createUser;

    @Column(value = "create_time")
    private LocalDateTime createTime;

    @Column(value = "update_time")
    private LocalDateTime updateTime;

}
