package com.duegin.notification.domain.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

/**
 * 用户订阅通知 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Table(value = "user_notification")
public class UserNotification {

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
    @Column(value = "notification_id")
    private Integer notificationId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }
}
